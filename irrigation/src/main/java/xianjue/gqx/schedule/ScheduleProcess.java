package xianjue.gqx.schedule;

public abstract class ScheduleProcess {
	/**
	 * ִ������Ľӿ�
	 * ÿ�δ����ݿ��л�ȡ����״̬���ж�����ִ���߼���ִ�У�����������״̬
	 */
	public abstract void execute();

	/**
	 * ������ζ�ʱ��������ʱ��
	 * @return
	 */
	public abstract String getDelayTime();
	
}
