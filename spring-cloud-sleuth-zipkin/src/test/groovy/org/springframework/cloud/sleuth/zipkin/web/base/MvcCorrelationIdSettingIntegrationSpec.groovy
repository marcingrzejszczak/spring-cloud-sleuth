package org.springframework.cloud.sleuth.zipkin.web.base

import org.springframework.cloud.sleuth.zipkin.web.correlationId.CorrelationIdFilter
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder

class MvcCorrelationIdSettingIntegrationSpec extends MvcWiremockIntegrationSpec {

	@Override
	protected void configureMockMvcBuilder(ConfigurableMockMvcBuilder mockMvcBuilder) {
		super.configureMockMvcBuilder(mockMvcBuilder)
		mockMvcBuilder.addFilter(new CorrelationIdFilter())
	}
}
