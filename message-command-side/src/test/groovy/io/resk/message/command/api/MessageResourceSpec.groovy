package io.resk.message.command.api

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken
import io.resk.message.command.vm.Message
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class MessageResourceSpec extends Specification {
	@Shared
	@AutoCleanup
	EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
	
	@Shared
	@AutoCleanup
	RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())
	
	def "Test Message Endpoint when Authentication is Enabled"() {
		Message message = new Message(null, null, null, null, null, null, null, null, null)

		when: 'Accessing a secured URL without authentication'
		  def request = HttpRequest.POST("/projects/sda/messages:send", message)
			.headers({ headers -> headers.add("X-API-VERSION", "1") })
			
	      client.toBlocking().exchange(request)
	
	    then: "returns unauthorized"
	      HttpClientResponseException e = thrown(HttpClientResponseException)
	      e.status == HttpStatus.UNAUTHORIZED
		  
		when: "Login endpoint is called with valid credentials"
		  UsernamePasswordCredentials creds = new UsernamePasswordCredentials("julius", "password")
		  request = HttpRequest.POST('/login', creds)
		  HttpResponse<BearerAccessRefreshToken> rsp = client.toBlocking().exchange(request, BearerAccessRefreshToken)
		  
		then: "token is returned"
		  rsp.status == HttpStatus.OK
		  
	    when: "Using token in next request to create message"
		  String accessToken = rsp.body().accessToken
		
		  request = HttpRequest.POST("/projects/sda/messages:send", message)
				.headers(
					{ headers -> 
					  headers.add("X-API-VERSION", "1") 
					  headers.add(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
					})
				
		  HttpResponse<String> resp = client.toBlocking().exchange(request)
		
		then: "returns accepted"
		  resp.status == HttpStatus.ACCEPTED
		
		when: "Creating a new message with versioning in parameter"
		  def uri = UriBuilder.of('/projects/{projectId}/messages:send')
		  			      .queryParam("api-version", "1")
						  .expand(Collections.singletonMap("projectId", "demo"))
		  request = HttpRequest.POST(uri, message).header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
				
		  resp = client.toBlocking().exchange(request)
		
		then: "returns accepted"
		  resp.status == HttpStatus.ACCEPTED
	}
	
	def "Test Message Endpoint when Authentication is Disabled"() {
		
	}

}
