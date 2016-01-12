package xianjue.gqx.listener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import xianjue.gqx.util.ContextManager;
import xianjue.gqx.util.PropertiesReader;

public class GprsListener implements Runnable{
	private int port;
    private static ServerSocket serverSocket; 
    private boolean runflag = true;
    
    private Logger logger = Logger.getLogger(getClass());
    
    public GprsListener(){
    	Properties prop = new PropertiesReader().getProperties("/config.properties");

		String portStr = prop.getProperty("GprsListenerPort").trim();
		port = Integer.parseInt(portStr);
		logger.info(portStr);
    }
    
    public boolean isRunflag() {
		return runflag;
	}
	public void setRunflag(boolean runflag) {
		this.runflag = runflag;
	}
	
    public void run() { 
    	ApplicationContext ac = ContextManager.getApplicationContext();
    	
    	try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

    	 //开启接收与处理信息的服务  
        Executor es = Executors.newCachedThreadPool();//线程池  
        while(runflag) {
        	logger.info("gprs listener running !");
            Socket socket = null;  
            try {  
                socket = serverSocket.accept();  
                logger.info("new connection is completed " + socket.getInetAddress() + ":" + socket.getPort());
                if (socket != null) {  
//                	MessageHandler messageHandler = new MessageHandler();
                	MessageHandler messageHandler = (MessageHandler) ac.getBean("messageHandler");
                    messageHandler.setClient(socket);
                    es.execute(messageHandler);
                }  
            } catch(IOException e) { 
            	logger.error("gprs listener exception!");
                e.printStackTrace();  
            }  
        }  
    }  
	
    public static void main(String args[]){
    	GprsListener g = new GprsListener();
		Thread t = new Thread(g);
		t.start();
    	
    }
}
