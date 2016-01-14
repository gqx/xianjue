package xianjue.gqx.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.enums.ZigbeeTypeEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.web.service.GprsService;
import xianjue.gqx.web.service.ZigbeeService;

@Controller
@RequestMapping(value = "/zigbeeManage/")
public class ZigbeeManageController {
	
	private static Logger logger = Logger.getLogger(ZigbeeManageController.class);
	
	@Resource(name="zigbeeService")
	private ZigbeeService zigbeeService;
	
	@Resource(name="gprsService")
	private GprsService gprsService;
	
	@RequestMapping(value = "index2")
	public String index2(){
		return "createZigbee";
	}
	
	@RequestMapping(value = "index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("zigbeeManage");
		
		mav.addObject("gprsList",gprsService.getAllGprs());	
		mav.addObject("zigbeeTypes",ZigbeeTypeEnum.values());
		
		return mav;
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
				map.put("error", "gprs mac �����ڣ����������趨��");
			}else if(e.getErrorCode().equals(ErrorEnum.ZIGBEE_EXISTS.getCode())){
				map.put("error", "zigbee mac �Ѿ�����");
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
				map.put("error", "zigbee mac �����ڣ����������趨��");
			}
			map.put("success", false);
			return map;
		}
		
		map.put("success", true);
		return map;
	}
}
