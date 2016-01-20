package xianjue.gqx.quartz;

import org.springframework.stereotype.Service;

@Service
public class IrrigationTask implements Runnable{

	private TaskManager taskManager;
	
	private Process process;
	
	private CheckStatusProcess checkStatusProcess = new CheckStatusProcess();
	
	private InitIrrigationProcess initIrrigationProcess = new InitIrrigationProcess();
	
	@Override
	public void run() {
		checkStatusProcess.setIrrigationTask(this);
		initIrrigationProcess.setIrrigationTask(this);
		process = initIrrigationProcess;
		while(true){
			process.execute();
		}
		
	}
	
	public TaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public CheckStatusProcess getCheckStatusProcess() {
		return checkStatusProcess;
	}

	public void setCheckStatusProcess(CheckStatusProcess checkStatusProcess) {
		this.checkStatusProcess = checkStatusProcess;
	}

	public InitIrrigationProcess getInitIrrigationProcess() {
		return initIrrigationProcess;
	}

	public void setInitIrrigationProcess(InitIrrigationProcess initIrrigationProcess) {
		this.initIrrigationProcess = initIrrigationProcess;
	}
	
	

}
