package com.t248.lhd.crm.service.impl;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;

public class QuartzTest {
    public static void main(String[] args) throws SchedulerException {
        //创建调度器
        //将具体的作业类（RamJob）绑定到调度任务详情中
        //创建触发器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobDetail jobDetail = newJob(RamJob.class)
                .withDescription("this is a job")
                .withIdentity("job1","group1")
                .usingJobData("level","老")
                .build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("job","司机");


        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("this is a trigger1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(3,2))
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 42 17 * * ?"))
                .withIdentity("trigger1","group1")
                .build();

        //将触发器以及调度任务详情绑定到调度器上
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();
    }
    }

