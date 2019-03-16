package io.resk.message.command.api

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
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
	
	def "Test Message Endpoint"() {
		when: "Creating a new message"
		HttpRequest request = HttpRequest.POST("/projects/sda/messages:send", 
				new Message(null, null, null, null, null, null, null, null, null))
				.headers({ headers -> headers.add("X-API-VERSION", "1") })
				
		HttpResponse<String> rsp = client.toBlocking()
			.exchange(request)
		
		then: "Return accepted"
		rsp.status == HttpStatus.ACCEPTED
	}
}
