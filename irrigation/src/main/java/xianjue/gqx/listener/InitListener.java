package xianjue.gqx.listener;

import javax.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import xianjue.gqx.listener.service.GprsListenerService;

public class InitListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Resource(name="gprsListenerService")
	private GprsListenerService gprsListenerService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(arg0.getApplicationContext().getParent() != null){
			gprsListenerService.startGprsListener();
		}
	}
}
