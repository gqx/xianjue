package xianjue.gqx.enums;

public enum TaskTypeEnum {

	CHECK_STATUS("CHECK_STATUS","¼ì²âÊª¶È×´Ì¬ÈÎÎñ"),
	INIT_IRRIGATION("INIT_IRRIGATION","³õÊ¼»¯ÂÖ¹à"),
	RUN_IRRIGATION("RUN_IRRIGATION","Ö´ÐÐÂÖ¹à"),
	FINISH_IRRIGATION("FINISH_IRRIGATION","ÖÕÖ¹ÂÖ¹à");
	
	private String code;
	private String desc;
	
	TaskTypeEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public TaskTypeEnum getEnum(String code){
		for(TaskTypeEnum error : TaskTypeEnum.values()){
			if(error.code.equals(code)){
				return error;
			}
		}
		return null;
	}
}
