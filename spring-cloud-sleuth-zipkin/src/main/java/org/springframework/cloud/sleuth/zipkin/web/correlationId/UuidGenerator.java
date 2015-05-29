package org.springframework.cloud.sleuth.zipkin.web.correlationId;

import java.util.UUID;

public class UuidGenerator {
	public String create() {
		return UUID.randomUUID().toString();
	}
}
