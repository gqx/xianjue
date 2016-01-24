package xianjue.gqx.schedule;

import java.util.List;

/**
 * ����һ��������Щ����Ӧ�þ�����ͬ�Ķ�ʱ����
 * @author: gaoqixin 2016/1/24
 * @version: 1.0.0
 * @since: 1.0
 */
public class ScheduleThread implements Runnable {

    private List<ScheduleProcess> scheduleProcessList;

    public ScheduleThread(List<ScheduleProcess> scheduleProcessList){
        this.scheduleProcessList = scheduleProcessList;

    }

    @Override
    public void run() {
        for(ScheduleProcess scheduleProcess : scheduleProcessList){
            scheduleProcess.execute();
        }
    }
}
