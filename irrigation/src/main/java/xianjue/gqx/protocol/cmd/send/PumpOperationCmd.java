package xianjue.gqx.protocol.cmd.send;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.AttributeName;
import xianjue.gqx.util.HexConvert;

public class PumpOperationCmd extends Command{
	
	public PumpOperationCmd(String zigbeeMac){
		// 初始化cmd
		byte[] zigbeeMacBytes = HexConvert.hexStringToBytes(zigbeeMac);
		cmd = new byte[14];
		cmd[0] = AttributeName.STAR_TFLAG;
		cmd[1] = AttributeName.TOGPRS_LENGTH;
		cmd[2] = 0x01;
		cmd[3] = AttributeName.TO_GPRS_CMD;
		for (int i = 0; i < 8; i++) {
			cmd[i + 4] = zigbeeMacBytes[i];
		}
		cmd[12] = AttributeName.SWITCH_DO_NOTHING;
		//设置校验位
		cmd[13] = HexConvert.FCS(cmd, 1, 12);
	}
	
	public void setZigbeeMac(String zigbeeMac){
		//设置校验位
		cmd[13] = HexConvert.FCS(cmd, 1, 12);
	}
	
	public void setPumpState(byte state){
		//设置校验位
		cmd[13] = HexConvert.FCS(cmd, 1, 12);
	}
	
	
}
