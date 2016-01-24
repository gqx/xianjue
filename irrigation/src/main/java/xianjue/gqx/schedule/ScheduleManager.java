package xianjue.gqx.schedule;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ��scheduleProcessList�����úõ�������ScheduledExecutorService����
 * ���ṩdestroy����
 *
 * @author: gaoqixin 2016/1/24
 * @version: 1.0.0
 * @since: 1.0
 */
public class ScheduleManager {

    private static Logger logger = Logger.getLogger(ScheduleManager.class);

    private List<ScheduleProcess> scheduleProcessList = new ArrayList<ScheduleProcess>();

    private List<ScheduledExecutorService> executorServiceList = new ArrayList<ScheduledExecutorService>();

    private List<ScheduledFuture> scheduledFutureList = new ArrayList<ScheduledFuture>();

    private Map<String,List<ScheduleProcess>> timeMap = new HashMap<String, List<ScheduleProcess>>();

    /**
     * ��������ʱ����࣬����map��
     */
    @PostConstruct
    public void init(){
        logger.info("#init#---------------start---------------");
        for(ScheduleProcess scheduleProcess : scheduleProcessList){
            String key = scheduleProcess.getDelayTime();
            List<ScheduleProcess> list = timeMap.get(key);
            if(list == null){
                list = new ArrayList<ScheduleProcess>();
                timeMap.put(key,list);
            }
            list.add(scheduleProcess);
        }
        logger.info("#init#---------------finish---------------");
        execute();
    }

    /**
     * ִ��map�е�����
     */
    public void execute(){
        logger.info("#execute#---------------start---------------");
        for(String key : timeMap.keySet()){
            List<ScheduleProcess> list = timeMap.get(key);
            ScheduleThread scheduleThread = new ScheduleThread(list);
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, new IrrigationThreadFactory(key+"s-schedule", true));
            ScheduledFuture future = executorService.scheduleWithFixedDelay(scheduleThread,0,Long.valueOf(key),TimeUnit.SECONDS);

            this.executorServiceList.add(executorService);
            this.scheduledFutureList.add(future);
        }
        logger.info("#execute#---------------finish---------------");
    }

    /**
     * ֹͣ�����ٶ�ʱ����
     */
    @PreDestroy
    public void destroy(){
        logger.info("#destroy#---------------start---------------");
        for(ScheduledFuture future : scheduledFutureList) {
            try {
                if(future != null && !future.isCancelled()) {
                    future.cancel(true);
                }
            } catch (Throwable e) {
            }
        }
        logger.info("#destroy#---------------finish---------------");
    }

    public List<ScheduleProcess> getScheduleProcessList() {
        return scheduleProcessList;
    }

    public void setScheduleProcessList(List<ScheduleProcess> scheduleProcessList) {
        this.scheduleProcessList = scheduleProcessList;
    }

    public List<ScheduledExecutorService> getExecutorServiceList() {
        return executorServiceList;
    }

    public void setExecutorServiceList(List<ScheduledExecutorService> executorServiceList) {
        this.executorServiceList = executorServiceList;
    }

    public List<ScheduledFuture> getScheduledFutureList() {
        return scheduledFutureList;
    }

    public void setScheduledFutureList(List<ScheduledFuture> scheduledFutureList) {
        this.scheduledFutureList = scheduledFutureList;
    }

    public Map<String, List<ScheduleProcess>> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(Map<String, List<ScheduleProcess>> timeMap) {
        this.timeMap = timeMap;
    }
}