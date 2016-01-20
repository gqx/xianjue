package xianjue.gqx.quartz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {
	
	@Autowired
	private IrrigationTask irrigationTask;
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	public void setup(){
		irrigationTask.setTaskManager(this);
	    executor.submit(irrigationTask);
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public IrrigationTask getIrrigationTask() {
		return irrigationTask;
	}

	public void setIrrigationTask(IrrigationTask irrigationTask) {
		this.irrigationTask = irrigationTask;
	}	
	
}
