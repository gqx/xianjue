package xianjue.gqx.schedule.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * ³õÊ¼»¯ÂÖ¹à
 */
public class InitIrrigationProcess implements ScheduleProcess {

	private static Logger logger = Logger.getLogger(InitIrrigationProcess.class);

	@Value("${schedule.init-irrigation.delay}")
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
