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
	
	//scheduleManager��xml������
	@Resource(name="scheduleManager")
	private ScheduleManager scheduleManager;
	
	
	/**
	 * �����Զ��ֹ�����
	 */
	@Transactional
	@Override
	public void startAutoTask() throws GreenHouseException {
		
		logger.info("#startCheckStatusTask# start");
		
		List<TaskSchedule> list = taskScheduleDao.getByTaskStatus(TaskStatusEnum.RUN.getCode());
		//����������ǰ������Ƿ�����������ֹͣ
		if(list != null && list.size() > 0){
			logger.error("#startCheckStatusTask# "+ErrorEnum.NOT_ALL_TASK_STOP.getCode());
			throw new GreenHouseException(ErrorEnum.NOT_ALL_TASK_STOP);
		}
		
		list = taskScheduleDao.getByTaskType(TaskTypeEnum.CHECK_STATUS.getCode());
		if(list == null || list.size() == 0){
			logger.error("#startCheckStatusTask# "+ErrorEnum.NO_TASK_CONFIG.getCode());
			throw new GreenHouseException(ErrorEnum.NO_TASK_CONFIG);
		}
		
		//�������ݿ��е�����״̬
		for(TaskSchedule taskSchedule : list){
			taskSchedule.setTask_status(TaskStatusEnum.RUN.getCode());
			taskScheduleDao.update(taskSchedule);
		}
		
		scheduleManager.execute();
		
		logger.info("#startCheckStatusTask# finish");
	}
	
	/**
	 * ֹͣ�Զ��ֹ�����
	 */
	@Transactional
	@Override
	public void stopAutoTask() {
		logger.info("#stopAllTask# start");
		
		List<TaskSchedule> list = taskScheduleDao.getByTaskStatus(TaskStatusEnum.RUN.getCode());
		if(list != null && list.size() > 0){
			//�������ݿ��е�����״̬
			for(TaskSchedule taskSchedule : list){
				taskSchedule.setTask_status(TaskStatusEnum.STOP.getCode());
				taskScheduleDao.update(taskSchedule);
			}
		}
		
		scheduleManager.destroy();
		logger.info("#stopAllTask# finish");
	}

}
