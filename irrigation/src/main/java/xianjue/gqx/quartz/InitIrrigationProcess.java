package xianjue.gqx.quartz;

public class InitIrrigationProcess implements Process{

	private IrrigationTask irrigationTask;
	
	int i = 0;
	
	public IrrigationTask getIrrigationTask() {
		return irrigationTask;
	}

	public void setIrrigationTask(IrrigationTask irrigationTask) {
		this.irrigationTask = irrigationTask;
	}

	@Override
	public void execute() {
		i++;
		System.out.println("InitIrrigationProcess:"+i);
		if(i == 5){
			System.out.println("InitIrrigationProcess: setProcess CheckStatusProcess");
			irrigationTask.setProcess(irrigationTask.getCheckStatusProcess());
			i = 0;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
