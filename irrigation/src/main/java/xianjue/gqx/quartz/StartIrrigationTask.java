package xianjue.gqx.quartz;

import org.springframework.stereotype.Service;

@Service
public class StartIrrigationTask implements Runnable{

	@Override
	public void run() {
		
		for(int i=0;i<5;i++){
			System.out.println("StartIrrigationTask:"+i);
		}
		
		
	}

}
