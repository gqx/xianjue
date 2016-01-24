package xianjue.gqx.schedule;

public interface ScheduleProcess {
	/**
	 * 执行任务的接口
	 */
	public void execute();

	/**
	 * 获得两次定时任务间隔的时间
	 * @return
	 */
	public String getDelayTime();
}
