package xianjue.gqx.schedule;

public interface ScheduleProcess {
	/**
	 * 执行任务的接口
	 * 每次从数据库中获取任务状态，判断任务执行逻辑并执行，最后更新任务状态
	 */
	public void execute();

	/**
	 * 获得两次定时任务间隔的时间
	 * @return
	 */
	public String getDelayTime();
}
