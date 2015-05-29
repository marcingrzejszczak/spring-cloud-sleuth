package org.springframework.cloud.sleuth.zipkin.web.correlationId.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.cloud.sleuth.zipkin.web.correlationId.CorrelationIdHolder;
import org.springframework.cloud.sleuth.zipkin.web.correlationId.CorrelationIdUpdater;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.concurrent.Callable;

public abstract class CorrelatedCommand<R> extends HystrixCommand<R> {
    private final String clientCorrelationId = CorrelationIdHolder.get();

    protected CorrelatedCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    protected CorrelatedCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected final R run() throws Exception {
        return CorrelationIdUpdater.withId(clientCorrelationId, new Callable<R>() {
            @Override
            public R call() throws Exception {
                return doRun();
            }

        });
    }

    public abstract R doRun() throws Exception;
}
