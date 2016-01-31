package xianjue.gqx.schedule.process;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.TaskScheduleDao;
import xianjue.gqx.enums.TaskTypeEnum;
import xianjue.gqx.po.TaskSchedule;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * 检查湿度是否达标
 * 如果不达标，则开启轮灌
 */
public class CheckStatusProcess extends ScheduleProcess {

	private static Logger logger = Logger.getLogger(CheckStatusProcess.class);

	@Value("${schedule.check-status.delay}")
	private String delayTime;
	
	@Resource(name="taskScheduleDao")
	private TaskScheduleDao taskScheduleDao; 
	
	private final TaskTypeEnum TASKTYPE = TaskTypeEnum.CHECK_STATUS;

	@Override
	public String getDelayTime() {
		return delayTime;
	}

	@Transactional
	@Override
	public void execute() {
		logger.info(Thread.currentThread().getName()+" CheckStatusProcess execute");
		try{
			List<TaskSchedule> list = taskScheduleDao.getByTaskType(TASKTYPE.getCode());
			for(TaskSchedule taskSchedule : list){
				if(taskSchedule.getTask_status() == ""){
					//TODO
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


}
