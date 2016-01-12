package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.HexConvert;

public class ZigbeePumpStartCmd extends Command{
	
	public ZigbeePumpStartCmd(byte[] cmd){
		this.cmd = cmd;
	}
	
	public String getGprsMacStr(){
		String macStr = HexConvert.bytesToHexString(getGprsMacBytes());
		return  macStr;
	}
	
	public byte[] getGprsMacBytes(){
		byte[] mac = new byte[15];
		for (int i = 13; i < 28; i++) {
			mac[i - 13] = cmd[i];
		}
		return mac;
	}
	
	public String getZigbeeMacStr(){
		String macStr = HexConvert.bytesToHexString(getZigbeeMacBytes());
		return  macStr;
	}
	
	public byte[] getZigbeeMacBytes(){
		byte[] zigbeeMac = new byte[8];
		for (int i = 4; i < 12; i++) {
			zigbeeMac[i - 4] = cmd[i];
		}
		return zigbeeMac;
	}
	
	public int getPressure(){
		return Integer.parseInt(String.valueOf(cmd[12]));
	}
}
