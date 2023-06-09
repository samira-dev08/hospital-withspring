package com.company.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {
    @Scheduled(fixedRate = 3000)
    public void fixedRate(){
        System.out.println("HELLO SCHEDULE");
    }
}
