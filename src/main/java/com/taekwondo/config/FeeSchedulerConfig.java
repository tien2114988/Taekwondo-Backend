package com.taekwondo.config;

import com.taekwondo.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class FeeSchedulerConfig {
    @Autowired
    FeeService feeService;

    @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Ho_Chi_Minh")
    public void scheduleAddFee() {
        feeService.scheduleAddFee();
    }
}
