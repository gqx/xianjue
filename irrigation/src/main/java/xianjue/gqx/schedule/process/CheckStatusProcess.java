package xianjue.gqx.schedule.process;

import org.springframework.beans.factory.annotation.Value;
import xianjue.gqx.schedule.ScheduleProcess;

/**
 * ���ʪ���Ƿ���
 * �������꣬�����ֹ�
 */
public class CheckStatusProcess implements ScheduleProcess {

	@Value("${schedule.check-status.delay}")
	private String delayTime;

	@Override
	public void execute() {
		System.out.println("CheckStatusProcess: setScheduleProcess InitIrrigationProcess");
	}

	@Override
	public String getDelayTime() {
		return delayTime;
	}

}
