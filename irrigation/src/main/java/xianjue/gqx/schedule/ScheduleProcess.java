package xianjue.gqx.schedule;

public interface ScheduleProcess {
	/**
	 * ִ������Ľӿ�
	 * ÿ�δ����ݿ��л�ȡ����״̬���ж�����ִ���߼���ִ�У�����������״̬
	 */
	public void execute();

	/**
	 * ������ζ�ʱ��������ʱ��
	 * @return
	 */
	public String getDelayTime();
}
