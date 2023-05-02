package com.babel.vehiclerentingapproval.cron;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class simpleExampleCron {
    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync()  {
        System.out.println(
                "Fixed rate task async - " + System.currentTimeMillis() / 1000);
    }

    @Async
    @Scheduled(cron = "0 * * * * ?") //cada minuto
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);
    }
}
