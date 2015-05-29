package org.springframework.cloud.sleuth.zipkin.web.correlationId;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * Registers beans that add correlation id to requests
 *
 * @see CorrelationIdAspect
 * @see CorrelationIdFilter
 */
@Configuration
@ConditionalOnProperty(value = "spring.cloud.sleuth.zipkin.correlationid.enabled", matchIfMissing = true)
public class CorrelationIdAutoConfiguration {

	@Value("${spring.cloud.sleuth.zipkin.correlationid.skipPattern:}")
	private String skipPattern;

	@Bean
	@ConditionalOnMissingBean
	public CorrelationIdAspect correlationIdAspect() {
		return new CorrelationIdAspect();
	}

	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean correlationHeaderFilter(UuidGenerator uuidGenerator) {
		Pattern pattern = StringUtils.isBlank(skipPattern) ? Pattern.compile(skipPattern) : CorrelationIdFilter.DEFAULT_SKIP_PATTERN;
		return new FilterRegistrationBean(new CorrelationIdFilter(uuidGenerator, pattern));
	}

	@Bean
	@ConditionalOnMissingBean
	public UuidGenerator uuidGenerator() {
		return new UuidGenerator();
	}
}
