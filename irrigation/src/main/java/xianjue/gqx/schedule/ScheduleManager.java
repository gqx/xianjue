package xianjue.gqx.schedule;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 将scheduleProcessList中配置好的任务用ScheduledExecutorService运行
 * 并提供destroy方法
 *
 * @author: gaoqixin 2016/1/24
 * @version: 1.0.0
 * @since: 1.0
 */
public class ScheduleManager {

    private static Logger logger = Logger.getLogger(ScheduleManager.class);

    private List<ScheduleProcess> scheduleProcessList = new ArrayList<ScheduleProcess>();

    private List<ScheduledExecutorService> executorServiceList = new ArrayList<ScheduledExecutorService>();

    @SuppressWarnings("rawtypes")
	private List<ScheduledFuture> scheduledFutureList = new ArrayList<ScheduledFuture>();

    private Map<String,List<ScheduleProcess>> timeMap = new HashMap<String, List<ScheduleProcess>>();

    private ThreadFactory threadFactory = new IrrigationThreadFactory("schedule", true);
    /**
     * 将任务按照时间分类，放入map中
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
    }

    /**
     * 执行map中的任务
     */
    public void execute(){
        logger.info("#execute#---------------start---------------");
        //不能有重复任务，所以首先关闭之前的任务
        destroy();
        for(String key : timeMap.keySet()){
            List<ScheduleProcess> list = timeMap.get(key);
            ScheduleThread scheduleThread = new ScheduleThread(list);
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1,threadFactory);
            ScheduledFuture<?> future = executorService.scheduleWithFixedDelay(scheduleThread,0,Long.valueOf(key),TimeUnit.SECONDS);

            this.executorServiceList.add(executorService);
            this.scheduledFutureList.add(future);
        }
        logger.info("#execute#---------------finish---------------");
    }

    /**
     * 停止并销毁定时任务
     */
    @PreDestroy
    public void destroy(){
        logger.info("#destroy#---------------start---------------");
        for(ScheduledFuture<?> future : scheduledFutureList) {
            try {
                if(future != null && !future.isCancelled()) {
                    future.cancel(true);
                }
            } catch (Throwable e) {
            }
        }
        scheduledFutureList.clear();
        executorServiceList.clear();
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

    @SuppressWarnings("rawtypes")
	public List<ScheduledFuture> getScheduledFutureList() {
        return scheduledFutureList;
    }

    @SuppressWarnings("rawtypes")
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
