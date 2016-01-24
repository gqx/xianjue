package xianjue.gqx.schedule.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * ���ʪ���Ƿ���
 * �������꣬�����ֹ�
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
