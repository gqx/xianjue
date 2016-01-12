package xianjue.gqx.protocol.cmd;

import xianjue.gqx.enums.CommandTypeEnum;
import xianjue.gqx.util.HexConvert;

public abstract class Command {
	
	protected byte[] cmd;
	
	public void setCmd(byte[] cmdBytes){
		this.cmd = cmdBytes;
	}
	
	public byte[] getCmd(){
		return this.cmd;
	}
	
	public CommandTypeEnum getType(){
		return CommandTypeEnum.getEnum(cmd[3]);
	}
	
	/**
	 * 验证数据是否正确
	 * @return
	 */
	public boolean isValid(){
		if(cmd == null || cmd.length < 0){
			return false;
		}
		byte b = cmd[cmd.length-1];
		if(HexConvert.FCS(cmd, 1, cmd.length-2) != b){
			return false;
		}
		
		return true;
	}
}
