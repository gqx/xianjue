package xianjue.gqx.quartz;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class IrrigationTask implements Runnable{

	private TaskManager taskManager;
	
	int i = 0;
	
	@Override
	public void run() {
		i++;
		
		System.out.println("IrrigationTask:"+i);
		if(i > 3){
			System.out.println("IrrigationTask:shutdownNow");
			ScheduledExecutorService executor =  taskManager.getExecutor();
			executor.shutdownNow();
			if(executor.isShutdown()){
				System.out.println("IrrigationTask:StartIrrigationTask");
				executor.scheduleWithFixedDelay(taskManager.getIrrigationTask(), 0, 1, TimeUnit.SECONDS);
			}	
		}	
	}

	public TaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}
	
	

}
