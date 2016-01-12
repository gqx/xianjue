package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.util.AttributeName;

/**
 * zigbee���ص�ˮ��״̬����
 * @author gqx
 *
 */
public class ZigbeeResponseWaterValveCmd extends ZigbeeResponseCmd{
	
	public ZigbeeResponseWaterValveCmd(byte[] cmd){
		this.cmd = cmd;
	}

	
	/**
	 * �����豸˳���ȡ�豸״̬
	 * @param order �豸˳��
	 * @return
	 */
	public int getState(int order){
		byte state1 = cmd[19];
		byte state2 = cmd[20];
		int state = -1;
		if(order < 8){
			state = AttributeName.gorder[order] & state1;
		}else{
			state = AttributeName.gorder[order] & state2;
		}
		return state != 0?AttributeName.DEVICE_ON:AttributeName.DEVICE_OFF;
	}
}
