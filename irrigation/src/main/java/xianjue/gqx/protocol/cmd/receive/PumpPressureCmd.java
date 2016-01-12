package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.HexConvert;

/**
 * zigbee连接的压力传感器数据
 * @author gqx
 *
 */
public class PumpPressureCmd extends Command{

	public PumpPressureCmd(byte[] cmd){
		this.cmd = cmd;
	}
	
	public String getGprsMacStr(){
		String macStr = HexConvert.bytesToHexString(getGprsMacBytes());
		return  macStr;
	}
	
	public byte[] getGprsMacBytes(){
		byte[] mac = new byte[15];
		for (int i = 4; i < 19; i++) {
			mac[i - 4] = cmd[i];
		}
		return mac;
	}
	
	/**
	 * 获取电量值
	 * @return
	 */
	public int getIllumination(){
		int illuminationH = Integer.parseInt(String.valueOf(cmd[19]));
//		int illuminationL = Integer.parseInt(String.valueOf(cmd[20]));
		return illuminationH;
	}
	
	/**
	 * 获取压力值
	 * @return
	 */
	public int getPressure(){
		return Integer.parseInt(String.valueOf(cmd[20]));
	}
	
	/**
	 * 获取温度值
	 * @return
	 */
	public int getTemperature(){
		return Integer.parseInt(String.valueOf(cmd[21])) - 40;
	}
	
	/**
	 * 获取湿度值
	 * @return
	 */
	public int getHumidity(){
		return Integer.parseInt(String.valueOf(cmd[22])) - 40;
	}
	
	
}
