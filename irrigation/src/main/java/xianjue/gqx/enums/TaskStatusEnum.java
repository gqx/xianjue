package xianjue.gqx.enums;

public enum TaskStatusEnum {
	
	RUN("RUN","‘À––"),
	STOP("STOP","Õ£÷π");
	
	private String code;
	private String desc;
	
	TaskStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public TaskStatusEnum getEnum(String code){
		for(TaskStatusEnum error : TaskStatusEnum.values()){
			if(error.code.equals(code)){
				return error;
			}
		}
		return null;
	}
}
