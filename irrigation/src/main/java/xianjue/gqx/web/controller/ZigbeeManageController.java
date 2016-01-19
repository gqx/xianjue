package xianjue.gqx.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xianjue.gqx.enums.ZigbeeTypeEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.quartz.TaskManager;
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
	
	@Resource
	private TaskManager taskManager;
	
	@RequestMapping(value = "index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("zigbeeManage");
		
		mav.addObject("gprsList",gprsService.getAllGprs());	
		mav.addObject("zigbeeTypes",ZigbeeTypeEnum.values());
		mav.addObject("success",true);
		taskManager.setup();
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "getZigbeeByGprsAndType")
	public Map<String,Object> getZigbeeByGprsAndType(String gprsMac,int zigbeeType){
		logger.info("#getZigbeeByGprsAndType gprsMac["+gprsMac+"] zigbeeType["+zigbeeType+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("zigbeeList",zigbeeService.getZigbeeByGprsAndType(gprsMac, zigbeeType));
			map.put("success", true);
			return map;
		} catch (GreenHouseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			map.put("error", e.getErrorDesc());
			map.put("success", false);
			return map;
		}		
	}
	
	@ResponseBody
	@RequestMapping(value = "createZigbee")
	public Map<String,Object> createZigbee(String gprsMac, String zigbeeMac,String zigbeeName, Integer zigbeeType){
		logger.info("#createZigbee# gprsMac["+gprsMac+"] zigbeeMac["+zigbeeMac+"] zigbeeName["+zigbeeName+"] zigbeeType["+zigbeeType+"]");
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			zigbeeService.createZigbee(zigbeeMac, zigbeeName, gprsMac,zigbeeType);
			map.put("success", true);
			return map;
		} catch (GreenHouseException e) {
			logger.error("#createZigbee# error zigbeeMac["+zigbeeMac+"] zigbeeName["+zigbeeName+"] "+e.getErrorCode());
//			e.printStackTrace();
			map.put("error", e.getErrorDesc());
			map.put("success", false);
			return map;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updateZigbee")
	public Map<String,Object> updateZigbee(String oldMac, String newMac, String name){
		logger.info("#updateZigbee# oldMac["+oldMac+"] newMac["+newMac+"] name["+name+"]");
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			zigbeeService.updateZigbee(oldMac, newMac, name);
			map.put("success", true);
			return map;
		} catch (GreenHouseException e) {
			logger.error("#updateZigbee error oldMac["+oldMac+"] newMac["+newMac+"] name["+name+"]"+e.getErrorCode());
//			e.printStackTrace();
			map.put("error", e.getErrorDesc());
			map.put("success", false);
			return map;
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "deleteZigbee")
	public Map<String,Object> deleteZigbee(String zigbeeMac){
		logger.info("#deleteZigbee# zigbeeMac["+zigbeeMac+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			zigbeeService.deleteZigbeeByMac(zigbeeMac);
			map.put("success", true);
			return map;
		} catch (GreenHouseException e) {
			logger.error("#createZigbee# error zigbeeMac[" + zigbeeMac + "] " + e.getErrorCode());
			map.put("error", e.getErrorDesc());
			map.put("success", false);
			return map;
		}
	}
}
