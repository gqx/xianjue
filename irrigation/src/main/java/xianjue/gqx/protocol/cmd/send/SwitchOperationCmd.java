package xianjue.gqx.protocol.cmd.send;

import xianjue.gqx.protocol.cmd.Command;
import xianjue.gqx.util.AttributeName;
import xianjue.gqx.util.HexConvert;

public class SwitchOperationCmd extends Command{
	
	public SwitchOperationCmd(String zigbeeMac){
		// 初始化cmd
		byte[] zigbeeMacBytes = HexConvert.hexStringToBytes(zigbeeMac);
		cmd = new byte[17];
		cmd[0] = AttributeName.STAR_TFLAG;
		cmd[1] = AttributeName.TOGPRS_LENGTH;
		cmd[2] = 0x00;
		cmd[3] = AttributeName.TO_GPRS_CMD;
		for (int i = 0; i < 8; i++) {
			cmd[i + 4] = zigbeeMacBytes[i];
		}
		cmd[12] = AttributeName.SWITCH_DO_NOTHING;
		cmd[13] = AttributeName.SWITCH_DO_NOTHING;
		cmd[14] = AttributeName.SWITCH_DO_NOTHING;
		cmd[15] = AttributeName.SWITCH_DO_NOTHING;
		//设置校验位
		cmd[16] = HexConvert.FCS(cmd, 1, 15);
	}
	
	public void setZigbeeMac(String zigbeeMac){
		//设置校验位
		cmd[16] = HexConvert.FCS(cmd, 1, 15);
	}
	
	public void setSwitchState(int zorder,byte state){
		//设置校验位
		cmd[16] = HexConvert.FCS(cmd, 1, 15);
	}
	
	
}
