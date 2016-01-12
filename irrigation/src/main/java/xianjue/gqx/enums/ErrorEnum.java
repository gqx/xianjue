package xianjue.gqx.enums;

public enum ErrorEnum {
	GPRS_EXISTS("GPRS_EXISTS","gprsMac already exists"),
	GPRS_NOT_EXISTS("GPRS_NOT_EXISTS","gprsMac does not exist"),
	ZIGBEE_EXISTS("ZIGBEE_EXISTS","zigbeeMac already exists"),
	DB_ERROR("DB_ERROR","database error"),
	ZIGBEE_NOT_EXISTS("ZIGBEE_NOT_EXISTS","zigbee does not exist");
	
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
