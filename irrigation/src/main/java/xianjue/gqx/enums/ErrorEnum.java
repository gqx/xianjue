package xianjue.gqx.enums;

public enum ErrorEnum {
	GPRS_EXISTS("GPRS_EXISTS","gprs mac 已经存在"),
	GPRS_NOT_EXISTS("GPRS_NOT_EXISTS","gpr mac 不存在，必须事先设定好"),
	ZIGBEE_EXISTS("ZIGBEE_EXISTS","zigbee mac 已经存在"),
	DB_ERROR("DB_ERROR","数据库错误"),
	ZIGBEE_NOT_EXISTS("ZIGBEE_NOT_EXISTS","zigbee 不存在, 必须事先设定好"),
	ZIGBEE_TYPE_WRONG("ZIGBEE_TYPE_WRONG","zigbee 类型错误"),
	ZIGBEE_MAC_WRONG("ZIGBEE_MAC_WRONG","zigbee mac 错误"),
	
	NOT_ALL_TASK_STOP("NOT_ALL_TASK_STOP","已有任务正在执行,无法启动新任务"),
	NO_TASK_CONFIG("NO_TASK","没有配置任务");
	
	private String code;
	private String desc;
	
	ErrorEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public ErrorEnum getEnum(String code){
		for(ErrorEnum error : ErrorEnum.values()){
			if(error.code.equals(code)){
				return error;
			}
		}
		return null;
	}
	
}
