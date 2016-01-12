package xianjue.gqx.listener;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import xianjue.gqx.enums.CommandTypeEnum;
import xianjue.gqx.enums.DeviceTypeEnum;
import xianjue.gqx.enums.ZigbeeSubCommandTypeEnum;
import xianjue.gqx.listener.handler.GprsHandler;
import xianjue.gqx.listener.handler.PumpPressureHandler;
import xianjue.gqx.listener.handler.SensorDataHandler;
import xianjue.gqx.listener.handler.WaterValveHandler;
import xianjue.gqx.listener.handler.ZigbeePumpHandler;
import xianjue.gqx.listener.handler.ZigbeeValveHandler;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.protocol.cmd.receive.GprsStartCmd;
import xianjue.gqx.protocol.cmd.receive.PumpPressureCmd;
import xianjue.gqx.protocol.cmd.receive.SensorCmd;
import xianjue.gqx.protocol.cmd.receive.ZigbeePumpStartCmd;
import xianjue.gqx.protocol.cmd.receive.ZigbeeResponseWaterValveCmd;
import xianjue.gqx.protocol.cmd.receive.ZigbeeValveStartCmd;
import xianjue.gqx.util.HexConvert;

@Component("messageHandler")
@Scope("prototype")
public class MessageHandler implements Runnable{
	private Socket client;
	
	@Resource(name="gprsHandler")
	private GprsHandler gprsHandler;
//	@Resource(name="waterValveHandler")
	private WaterValveHandler waterValveHandler;
//	@Resource(name="sensorDataHandler")
	private SensorDataHandler sensorDataHandler;
	@Resource(name="zigbeeValveHandler")
	private ZigbeeValveHandler zigbeeValveHandler;
	@Resource(name="zigbeePumpHandler")
	private ZigbeePumpHandler zigbeePumpHandler;
	@Resource(name="pumpPressureHandler")
	private PumpPressureHandler pumpPressureHandler;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public MessageHandler() {
	}

	public MessageHandler(Socket cleint) {
		this.client = cleint;
	}

	@Override
	public void run() {
		while(true){
			logger.info("wating for message...");
			try {
				InputStream is = client.getInputStream();
				byte[] b = new byte[64];
				int length = is.read(b);// 读取信息
				
				String ip = client.getInetAddress().getHostAddress() + ": ";

				logger.info("accept new message:length["+length+"] data["+HexConvert.bytesToHexString(b)+"]");
				processMessage(b,ip);
			} catch (IOException e) {
				logger.error("message handler exception");
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 处理cmd数据
	 * 
	 * @param b
	 * @return
	 */
	private boolean processMessage(byte[] b,String ip) {
		byte[] cmd = b;
		CommandTypeEnum cmdType = CommandTypeEnum.getEnum(cmd[3]);
	    logger.info("accept cmd "+cmdType.getDesc());
	    
		if(cmdType == CommandTypeEnum.GPRS_STRAT){
			logger.info("new GprsStartCmd. start gprs");
			GprsStartCmd command = new GprsStartCmd(cmd);
			boolean result = gprsHandler.start(command, ip);
			if(result){
				logger.info("new GprsStartCmd. bindSocket: gprsMac["+command.getGprsMacStr()+"]");
				bindSocket(command.getGprsMacStr());
			}	
			
		}else if (cmdType == CommandTypeEnum.ZIGBEE_START){
			ZigbeeSubCommandTypeEnum subCmd = ZigbeeSubCommandTypeEnum.getEnum(cmd[2]);
			if(subCmd == ZigbeeSubCommandTypeEnum.VATER_VALVE_START){
				logger.info("new ZigbeeWaterValveStartCmd. start zigbee");
				ZigbeeValveStartCmd command = new ZigbeeValveStartCmd(cmd);
				zigbeeValveHandler.start(command, ip);
			}else if(subCmd == ZigbeeSubCommandTypeEnum.PUMP_START){
				logger.info("new ZigbeePumpStartCmd. start zigbee");
				ZigbeePumpStartCmd command = new ZigbeePumpStartCmd(cmd);
				zigbeePumpHandler.start(command, ip);
			}else if(subCmd == ZigbeeSubCommandTypeEnum.PRESSURE_DATA){
				logger.info("new PumpPressureCmd. add pressure");
				PumpPressureCmd command = new PumpPressureCmd(cmd);
				pumpPressureHandler.addPumpPressure(command);
			}
				
		}else if(cmdType == CommandTypeEnum.ZIGBEE_RESPONSE){
			DeviceTypeEnum deviceType = DeviceTypeEnum.getEnum(cmd[2]);
			if(deviceType == DeviceTypeEnum.WATER_VALVE){
				ZigbeeResponseWaterValveCmd command = new ZigbeeResponseWaterValveCmd(cmd);
				logger.info("new ZigbeeResponseWaterValveCmd. updateWaterValve: gprsMac["+command.getGprsMacStr()+"]");
				waterValveHandler.updateWaterValve(command);
			}
		}else if(cmdType == CommandTypeEnum.FROM_MAINZIGBEE){
			SensorCmd command = new SensorCmd(cmd);
			logger.info("new SensorCmd. updateSensorData: gprsMac["+command.getGprsMacStr()+"]");
			updateSocket(command.getGprsMacStr());
			sensorDataHandler.update((SensorCmd)command,ip);
		}
		
		return true;
	}

	
	
	//将socket存入socketHolder
	private void bindSocket(String gprsMac){
		SocketHolder.getInstance().setSocket(gprsMac, client);
		
	}
	
	private void updateSocket(String gprsMac){
		Gprs gprs = gprsHandler.getGprsByMac(gprsMac);
		if(gprs != null){
			SocketHolder.getInstance().setSocket(gprs.getMac(), client);
		}
	}
	
}
