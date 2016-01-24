package xianjue.gqx.schedule;

import java.util.List;

/**
 * 运行一组任务，这些任务应该具有相同的定时配置
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
