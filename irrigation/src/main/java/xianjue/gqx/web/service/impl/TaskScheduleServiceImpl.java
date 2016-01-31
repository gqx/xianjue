package xianjue.gqx.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.TaskScheduleDao;
import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.enums.TaskStatusEnum;
import xianjue.gqx.enums.TaskTypeEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.TaskSchedule;
import xianjue.gqx.schedule.ScheduleManager;
import xianjue.gqx.web.service.TaskScheduleService;

@Component("taskScheduleService")
public class TaskScheduleServiceImpl implements TaskScheduleService{

	private static Logger logger = Logger.getLogger(TaskScheduleServiceImpl.class);
	
	@Resource(name="taskScheduleDao")
	private TaskScheduleDao taskScheduleDao;
	
	//scheduleManager在xml中配置
	@Resource(name="scheduleManager")
	private ScheduleManager scheduleManager;
	
	
	/**
	 * 开启自动轮灌任务
	 */
	@Transactional
	@Override
	public void startAutoTask() throws GreenHouseException {
		
		logger.info("#startCheckStatusTask# start");
		
		List<TaskSchedule> list = taskScheduleDao.getByTaskStatus(TaskStatusEnum.RUN.getCode());
		//开启新任务前，检查是否所有任务都已停止
		if(list != null && list.size() > 0){
			logger.error("#startCheckStatusTask# "+ErrorEnum.NOT_ALL_TASK_STOP.getCode());
			throw new GreenHouseException(ErrorEnum.NOT_ALL_TASK_STOP);
		}
		
		list = taskScheduleDao.getByTaskType(TaskTypeEnum.CHECK_STATUS.getCode());
		if(list == null || list.size() == 0){
			logger.error("#startCheckStatusTask# "+ErrorEnum.NO_TASK_CONFIG.getCode());
			throw new GreenHouseException(ErrorEnum.NO_TASK_CONFIG);
		}
		
		//更新数据库中的任务状态
		for(TaskSchedule taskSchedule : list){
			taskSchedule.setTask_status(TaskStatusEnum.RUN.getCode());
			taskScheduleDao.update(taskSchedule);
		}
		
		scheduleManager.execute();
		
		logger.info("#startCheckStatusTask# finish");
	}
	
	/**
	 * 停止自动轮灌任务
	 */
	@Transactional
	@Override
	public void stopAutoTask() {
		logger.info("#stopAllTask# start");
		
		List<TaskSchedule> list = taskScheduleDao.getByTaskStatus(TaskStatusEnum.RUN.getCode());
		if(list != null && list.size() > 0){
			//更新数据库中的任务状态
			for(TaskSchedule taskSchedule : list){
				taskSchedule.setTask_status(TaskStatusEnum.STOP.getCode());
				taskScheduleDao.update(taskSchedule);
			}
		}
		
		scheduleManager.destroy();
		logger.info("#stopAllTask# finish");
	}

}
