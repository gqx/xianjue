package xianjue.gqx.listener;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import xianjue.gqx.util.HexConvert;

public class SocketHolder {
	private Map<String,Socket> socketMap = new ConcurrentHashMap<String,Socket>();
	private static SocketHolder holder = new SocketHolder();
	private Logger logger = Logger.getLogger(getClass());
	private SocketHolder(){}
	
	public static SocketHolder getInstance(){
		return holder;
	}

	public Map<String, Socket> getSocketMap() {
		return socketMap;
	}

	public void setSocketMap(Map<String, Socket> socketMap) {
		this.socketMap = socketMap;
	}
	
	public void setSocket(String key, Socket socket){
		if(socketMap.get(key) != null){
			socketMap.remove(key);		
		}
		socketMap.put(key, socket);
		if(socketMap.containsKey(key))
			logger.info("finish setSocket key:"+key);
		
	}
	
	public Socket getSocket(String key){
		return socketMap.get(key);
	}
	
	/**
	 * 通过key找到socket，发送message
	 * @param key
	 * @param message
	 * @return
	 */
	public int sendMessage (String key,byte[] message) {
		logger.info("will send message: "+HexConvert.bytesToHexString(message));
		Socket socket = getSocket(key);
	
		if(socket != null){
			synchronized(socket){
				logger.info("get socket, key="+key);
				try {
					OutputStream os = socket.getOutputStream();
					os.write(message);
					os.flush();
					//keep gprs receive right message
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}// 发送信息
				return 1;
			}
		}else{
			logger.info("socket is null, key="+key);
//			System.out.println("socket is null, key="+key);
			return -1;
		}
	}
	
}
