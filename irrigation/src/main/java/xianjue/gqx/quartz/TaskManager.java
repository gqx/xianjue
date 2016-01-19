package xianjue.gqx.quartz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {
	
	@Autowired
	private IrrigationTask irrigationTask;
	
	@Autowired
	private StartIrrigationTask startIrrigationTask;
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
	
	public void setup(){
		irrigationTask.setTaskManager(this);
	    executor.scheduleWithFixedDelay(irrigationTask,0,1,TimeUnit.SECONDS);
	}

	public ScheduledExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ScheduledExecutorService executor) {
		this.executor = executor;
	}

	public IrrigationTask getIrrigationTask() {
		return irrigationTask;
	}

	public void setIrrigationTask(IrrigationTask irrigationTask) {
		this.irrigationTask = irrigationTask;
	}

	public StartIrrigationTask getStartIrrigationTask() {
		return startIrrigationTask;
	}

	public void setStartIrrigationTask(StartIrrigationTask startIrrigationTask) {
		this.startIrrigationTask = startIrrigationTask;
	}
	
	
	
	
}
