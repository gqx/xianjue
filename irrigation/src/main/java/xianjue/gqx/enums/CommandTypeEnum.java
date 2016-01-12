package xianjue.gqx.enums;


/**
 * 与gprs通信的命令
 * @author gqx
 *
 */
public enum CommandTypeEnum {
	// 来自gprs启动时的数据
	GPRS_STRAT((byte) 0xD1,"GPRS_STRAT"),
	// 来自zigbee（子端）启动时的数据
	ZIGBEE_START((byte) 0xD2,"ZIGBEE_START"),
	// 来自zigbee（总端）上传的数据,心跳包
	FROM_MAINZIGBEE((byte) 0xD3,"FROM_MAINZIGBEE"),
	// 来自zigbee（子端）水阀状态有误报警数据
	PRESSURE_ZIGBEE((byte) 0xD4,"PRESSURE_ZIGBEE"),
	// 发送至gprs阀门控制数据
	TO_GPRS_CMD((byte) 0xD5,"TO_GPRS_CMD"),
	// zigbee反馈
	ZIGBEE_RESPONSE((byte) 0xD6,"ZIGBEE_RESPONSE");
	
	private byte code;
	private String desc;
	
	CommandTypeEnum(byte code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public byte getCode(){
		return this.code;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public static CommandTypeEnum getEnum(byte code){
		for(CommandTypeEnum cmd:CommandTypeEnum.values()){
			if(cmd.getCode() == code){
				return cmd;
			}
		}
		return null;
	}
}
