package br.com.car.adapters.configuration.circuitbreaker

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfiguration {

    fun getConfiguration() = CircuitBreakerConfig.custom()
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
        .slidingWindowSize(2)
        .slowCallRateThreshold(70.0f)
        .failureRateThreshold(70.0f)
        .waitDurationInOpenState(Duration.ofSeconds(20))
        .slowCallDurationThreshold(Duration.ofSeconds(3))
        .permittedNumberOfCallsInHalfOpenState(3)
        .build()

    fun getCircuiteBreaker() = CircuitBreakerRegistry
        .of(getConfiguration())
        .circuitBreaker("circuit-breaker-car-service")

}