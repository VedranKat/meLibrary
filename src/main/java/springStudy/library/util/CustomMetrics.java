package springStudy.library.util;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Basic counter object
 * Lookt it up @ http://localhost:8080/actuator/metrics/my_counter
 */
@Component
public class CustomMetrics {

    private final Counter myCounter;

    public CustomMetrics(MeterRegistry registry) {
        myCounter = Counter.builder("my_counter")
                .description("A custom counter")
                .register(registry);
    }

    public void incrementCounter() {
        myCounter.increment();
    }
}

