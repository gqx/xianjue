package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.HexConvert;


/**
 * ��������zigbee����ʱ��������˵�����
 * @author gqx
 *
 */
public class ZigbeeValveStartCmd extends Command{
	
	public ZigbeeValveStartCmd(byte[] cmd) {
		this.cmd = cmd;
	}
	
	public String getGprsMacStr(){
		String macStr = HexConvert.bytesToHexString(getGprsMacBytes());
		return  macStr;
	}
	
	public byte[] getGprsMacBytes(){
		byte[] mac = new byte[15];
		for (int i = 16; i < 31; i++) {
			mac[i - 16] = cmd[i];
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
	
	/**
	 * ��ȡzigbee�з��ŵ�״̬
	 * @param n ���ŵ����
	 * @return
	 */
	public int getSwitchState(int n){
		return cmd[11+n]==0x01?1:0;
	}
}
