package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.HexConvert;

/**
 * gprs启动时传给服务端的数据
 * @author gqx
 *
 */
public class GprsStartCmd extends Command{
	
	public GprsStartCmd(byte[] cmd){
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
	
}
