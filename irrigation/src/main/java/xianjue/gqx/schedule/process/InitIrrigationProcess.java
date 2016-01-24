package xianjue.gqx.schedule.process;

import org.springframework.beans.factory.annotation.Value;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * ³õÊ¼»¯ÂÖ¹à
 */
public class InitIrrigationProcess implements ScheduleProcess {

	@Value("${schedule.init-irrigation.delay}")
	private String delayTime;

	@Override
	public void execute() {
		System.out.println("InitIrrigationProcess: setScheduleProcess CheckStatusProcess");
	}

	@Override
	public String getDelayTime() {
		return delayTime;
	}

}
