package io.resk.message.command.version.resolution;

import java.util.Optional;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.web.router.version.RoutesVersioningConfiguration;
import io.micronaut.web.router.version.resolution.HeaderVersionResolver;
import io.micronaut.web.router.version.resolution.HeaderVersionResolverConfiguration;

@Replaces(HeaderVersionResolver.class)
@Requires(beans = { RoutesVersioningConfiguration.class, HeaderVersionResolverConfiguration.class })
public class DefaultHeaderVersionResolver extends HeaderVersionResolver {
	@Value("${resk.version.default:1}")
	private String version = "1";

	public DefaultHeaderVersionResolver(HeaderVersionResolverConfiguration configuration) {
		super(configuration);
	}

	@Override
	public Optional<String> resolve(HttpRequest<?> request) {
		return super.resolve(request)
		// TODO check if Controller method has @Version before setting default
		.or(() -> Optional.of(version));
	}

}
