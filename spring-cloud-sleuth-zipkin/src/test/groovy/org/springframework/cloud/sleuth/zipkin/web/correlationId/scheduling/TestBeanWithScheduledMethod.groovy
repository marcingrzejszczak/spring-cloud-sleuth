package org.springframework.cloud.sleuth.zipkin.web.correlationId.scheduling

import org.springframework.cloud.sleuth.zipkin.web.correlationId.CorrelationIdHolder
import org.springframework.scheduling.annotation.Scheduled

class TestBeanWithScheduledMethod {

	String correlationId

	@Scheduled(fixedDelay = 50L)
	void scheduledMethod() {
		correlationId = CorrelationIdHolder.get()
	}

}
