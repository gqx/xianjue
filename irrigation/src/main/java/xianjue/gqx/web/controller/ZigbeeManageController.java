package xianjue.gqx.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.web.service.ZigbeeService;

@Controller
@RequestMapping(value = "/zigbeeManage/")
public class ZigbeeManageController {
	
	private static Logger logger = Logger.getLogger(ZigbeeManageController.class);
	
	@Resource(name="zigbeeService")
	private ZigbeeService zigbeeService;
	
	@RequestMapping(value = "index")
	public String index(){
		return "createZigbee";
	}
	
	@ResponseBody
	@RequestMapping(value = "createZigbee")
	public Map<String,Object> createZigbee(String gprsMac, String zigbeeMac,String zigbeeName, int zigbeeType){
		logger.info("#createZigbee gprsMac["+gprsMac+"] zigbeeMac["+zigbeeMac+"] zigbeeName["+zigbeeName+"] zigbeeType["+zigbeeType+"]");
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			zigbeeService.createZigbee(zigbeeMac, zigbeeName, gprsMac,zigbeeType);
		} catch (GreenHouseException e) {
			logger.error("#createZigbee error zigbeeMac["+zigbeeMac+"] zigbeeName["+zigbeeName+"] "+e.getErrorCode());
//			e.printStackTrace();
			if(e.getErrorCode().equals(ErrorEnum.GPRS_NOT_EXISTS.getCode())){
				map.put("error", "gprs mac 不存在，必须事先设定好");
			}else if(e.getErrorCode().equals(ErrorEnum.ZIGBEE_EXISTS.getCode())){
				map.put("error", "zigbee mac 已经存在");
			}
			map.put("success", false);
			return map;
		}
		
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "changeZigbeeMac")
	public Map<String,Object> changeZigbeeMac(String oldMac, String zigbeeMac,String newMac){
		logger.info("#changeZigbeeMac oldMac["+oldMac+"] newMac["+newMac+"]");
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			zigbeeService.changeZigbeeMac(oldMac, newMac);
		} catch (GreenHouseException e) {
			logger.error("#changeZigbeeMac error oldMac["+oldMac+"] newMac["+newMac+"]"+e.getErrorCode());
//			e.printStackTrace();
			if(e.getErrorCode().equals(ErrorEnum.ZIGBEE_NOT_EXISTS.getCode())){
				map.put("error", "zigbee mac 不存在，必须事先设定好");
			}
			map.put("success", false);
			return map;
		}
		
		map.put("success", true);
		return map;
	}
}
