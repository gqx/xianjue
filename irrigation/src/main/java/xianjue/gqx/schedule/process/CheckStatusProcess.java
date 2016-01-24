package xianjue.gqx.schedule.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * 检查湿度是否达标
 * 如果不达标，则开启轮灌
 */
public class CheckStatusProcess implements ScheduleProcess {

	private static Logger logger = Logger.getLogger(CheckStatusProcess.class);

	@Value("${schedule.check-status.delay}")
	private String delayTime;

	@Override
	public void execute() {
		logger.info(Thread.currentThread().getName()+" execute");
	}

	@Override
	public String getDelayTime() {
		return delayTime;
	}

}
