package org.springframework.cloud.sleuth.zipkin.web.correlationId.scheduling;

import org.springframework.cloud.sleuth.zipkin.web.correlationId.UuidGenerator ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Registers beans related to task scheduling.
 *
 * @see ScheduledTaskWithCorrelationIdAspect
 */
@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
public class TaskSchedulingConfiguration {
    @Bean
    public ScheduledTaskWithCorrelationIdAspect scheduledTaskPointcut(UuidGenerator uuidGenerator) {
        return new ScheduledTaskWithCorrelationIdAspect(uuidGenerator);
    }

}
