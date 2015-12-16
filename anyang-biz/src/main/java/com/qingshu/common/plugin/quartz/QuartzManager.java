/**
 * 
 */
package com.qingshu.common.plugin.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jl_love
 * 
 *         2011-10-14 豁达，坚强，勤奋
 */
public class QuartzManager {
	private static Scheduler scheduler;

	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("quartzDynamic.xml");
		scheduler = (StdScheduler) context.getBean("schedulerFactory");
	}

	/**
	 * 启动一个自定义的job
	 * 
	 * @param schedulingJob
	 *            自定义的job
	 * @param paramsMap
	 *            传递给job执行的数据
	 * @param isStateFull
	 *            是否是一个同步定时任务，true：同步，false：异步
	 * @return 成功则返回true，否则返回false
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static boolean enableCronSchedule(CustomJob schedulingJob, JobDataMap paramsMap, boolean isStateFull) {
		if (schedulingJob == null) {
			return false;
		}
		try {
			Trigger trigger = null;
	
			JobDetail jobDetail = null;
			if (null == trigger) {// 如果不存在该trigger则创建一个

				if (isStateFull) {
					jobDetail = newJob(schedulingJob.getStateFulljobExecuteClass()).withIdentity(schedulingJob.getJobId(), schedulingJob.getJobGroup()).build();

				} else {
					jobDetail = newJob(schedulingJob.getJobExecuteClass()).withIdentity(schedulingJob.getJobId(), schedulingJob.getJobGroup()).build();

				}
				
				trigger = newTrigger().withIdentity(schedulingJob.getTriggerName(), schedulingJob.getJobGroup()).startAt(new Date()).withSchedule(simpleSchedule().withIntervalInSeconds(15).repeatForever()).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {

				scheduler.rescheduleJob(trigger.getKey(), trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 禁用一个job
	 * 
	 * @param jobId
	 *            需要被禁用的job的ID
	 * @param jobGroupId
	 *            需要被警用的jobGroupId
	 * @return 成功则返回true，否则返回false
	 */
	public static boolean disableSchedule(String jobId, String jobGroupId) {
		if (jobId.equals("") || jobGroupId.equals("")) {
			return false;
		}
		try {
			Trigger trigger = getJobTrigger(jobId, jobGroupId);
			if (null != trigger) {
				scheduler.deleteJob(trigger.getJobKey());
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 得到job的详细信息
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的详细信息,如果job不存在则返回null
	 */
	public static JobDetail getJobDetail(String jobId, String jobGroupId) {
		if (jobId.equals("") || jobGroupId.equals("") || null == jobId || jobGroupId == null) {
			return null;
		}
		try {
			Trigger trigger = getJobTrigger(jobId, jobGroupId);
			return scheduler.getJobDetail(trigger.getJobKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到job对应的Trigger
	 * 
	 * @param jobId
	 *            job的ID
	 * @param jobGroupId
	 *            job的组ID
	 * @return job的Trigger,如果Trigger不存在则返回null
	 */
	public static Trigger getJobTrigger(String jobId, String jobGroupId) {
		if (jobId.equals("") || jobGroupId.equals("") || null == jobId || jobGroupId == null) {
			return null;
		}

		return newTrigger().forJob(jobId, jobGroupId).build();

	}

}
