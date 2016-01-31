package xianjue.gqx.web.service;

import xianjue.gqx.exception.GreenHouseException;

public interface TaskScheduleService {
	public void startAutoTask() throws GreenHouseException;
	public void stopAutoTask();
}
