package com.expensetracker.app.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ExpenseHealthIndicatorConfig {

    @Bean
    public ExpenseHealthIndicator expenseHealthCheck() {
        return new ExpenseHealthIndicator();
    }

    @Bean
    public HealthIndicator expenseHealthIndicator(ExpenseHealthIndicator expenseHealthCheck) {
        return () -> expenseHealthCheck.isHealthy() ?
                    Health.up().withDetail("expense-module", "Healthy").build() :
                    Health.down().withDetail("expense-module", "DB Connection Failed").build();
    }

}
