package com.huanzi.juc.code.ThreadPool.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Timer;
import java.util.TimerTask;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-09 10:38
 * @email: 18271645764@163.com
 **/
public class TimerTest {

    public static void main(String[] args) throws SchedulerException {

    }

    /**
     * todo 每隔5s执行(有问题)
     * @throws SchedulerException
     */
    private static void quartz() throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/5 * * * * ?")).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job,trigger);
    }

    class SimpleJob implements Job{
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("=====================" + System.currentTimeMillis());
        }
    }


    public static void timer(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //todo 每隔1s执行一次,如果任务超过1s!会等待
                System.out.println(System.currentTimeMillis());
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        //
        timer.schedule(task,1000,1000);
    }
}
