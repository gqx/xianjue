package xianjue.gqx.protocol.cmd.receive;

import xianjue.gqx.util.AttributeName;

/**
 * zigbee返回的水阀状态数据
 * @author gqx
 *
 */
public class ZigbeeResponseWaterValveCmd extends ZigbeeResponseCmd{
	
	public ZigbeeResponseWaterValveCmd(byte[] cmd){
		this.cmd = cmd;
	}

	
	/**
	 * 根据设备顺序获取设备状态
	 * @param order 设备顺序
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
