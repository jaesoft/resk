package io.resk.message.command.repository;

import org.testcontainers.containers.GenericContainer
import org.testcontainers.spock.Testcontainers

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Property
import io.micronaut.context.env.Environment
import io.micronaut.context.env.PropertySource
import io.micronaut.test.annotation.MicronautTest
import io.resk.message.command.domain.User
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification;

@Testcontainers
class UserRepositorySpec extends Specification {
	@Shared
	GenericContainer<?> postgres = new GenericContainer<>("postgres:11") //
	.withExposedPorts(5432) //
	.withEnv("POSTGRES_PASSWORD", "password") //
	.withEnv("POSTGRES_USER", "postgres")

	@Shared
	@AutoCleanup
	ApplicationContext context

	def setupSpec() {
		context = ApplicationContext.run(Map.of(
			"postgres.reactive.client.host", postgres.getContainerIpAddress(),
			"postgres.reactive.client.user", "postgres",
			"postgres.reactive.client.password", "password",
			"postgres.reactive.client.port", postgres.getMappedPort(5432),
			"postgres.reactive.client.database", "postgres"
			), Environment.TEST)
	}

	void "Test saving of User"() {
		when: "Getting a bean no error is raised"
		  UserRepository repository = context.getBean(UserRepository)
		  
		then:
		  noExceptionThrown()
		  
	    when: "User is saved record is added"
		  User user = new User()
		  user.setId(UUID.randomUUID())
		  user.setEmail("admin@resk.io")
		  user.setUsername("admin")
		  user.setPassword("admin")
		  
		  def single = repository.save(user)
		  
		then:
		  single.subscribe()
	}
}