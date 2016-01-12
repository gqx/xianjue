package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.enums.DeviceTypeEnum;
import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.HexConvert;

/**
 * zigbee返回接收到命令的响应
 * @author gqx
 *
 */
public  class ZigbeeResponseCmd extends Command{
	
	public DeviceTypeEnum getDeviceType(){
		return DeviceTypeEnum.getEnum(cmd[2]);
	}
	
	public byte[] getGprsMacBytes(){
		 byte[]	zigbeeMac = new byte[15];
			for(int i = 0; i < 15;i++){
				zigbeeMac[i] = cmd[i+4];	
			}
		return zigbeeMac;
	}
	
	public String getGprsMacStr(){
		String macStr = HexConvert.bytesToHexString(getGprsMacBytes());
		return macStr;
	}
	
}
