package xianjue.gqx.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements Job {
Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String s = arg0.getJobDetail().getJobDataMap().getString("str");
		logger.info(s);
	}

	public static void main(String[] args) throws SchedulerException, ParseException{
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date da = sdf.parse("2015-7-15 13:00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		System.out.println(cal.get(Calendar.MONTH));
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();
		JobDetail jd = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
		jd.getJobDataMap().put("str", "hello");
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("t1","tgroup1").startAt(da).withSchedule(CronScheduleBuilder.cronSchedule("0/0 36 19 15/1 7 ? 2015")).build();
		scheduler.scheduleJob(jd, trigger);
		
//		JobDetail jd2 = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group1").build();
//		jd2.getJobDataMap().put("str", "world");
//		Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("t2","tgroup1").startNow().withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build();
//		scheduler.scheduleJob(jd2, trigger2);
		scheduler.start();
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		JobKey jobKey = new JobKey("job1","group1");
//		scheduler.deleteJob(jobKey);
	}
}
