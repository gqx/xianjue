package xianjue.gqx.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.schedule.ScheduleManager;
import xianjue.gqx.web.service.GprsService;

@Controller
@RequestMapping(value = "/gprsManage/")
public class GprsManageContoller {
	private static Logger logger = Logger.getLogger(GprsManageContoller.class);
	
	@Resource(name="gprsService")
	private GprsService gprsService;

	@Resource(name="scheduleManager")
	private ScheduleManager scheduleManager;

	@RequestMapping(value = "index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("gprsManage");
		List<Gprs> list = gprsService.getAllGprs();
		mav.addObject("gprsList", list);
		scheduleManager.destroy();
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "addGprs")
	public Map<String,Object> addGprs(String gprsMac,String gprsName){
		logger.info("#createGprs mac["+gprsMac+"] name["+gprsName+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			gprsService.createGprs(gprsMac, gprsName);
		} catch (GreenHouseException e) {
			logger.error("#createGprs error mac["+gprsMac+"] name["+gprsName+"] "+e.getErrorCode());
			e.printStackTrace();
			if(e.getErrorCode().equals(ErrorEnum.GPRS_EXISTS.getCode())){
				map.put("error", "gprs mac ÒÑ¾­´æÔÚ");
			}
			map.put("success", false);
			return map;
			
		}
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "changeGprsMac")
	public Map<String,Object> changeGprsMac(String oldMac,String newMac){
		logger.info("#changeGprsMac oldMac["+oldMac+"] newMac["+newMac+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			gprsService.changeGprs(oldMac, newMac,null);
		} catch (GreenHouseException e) {
			logger.error("#changeGprsMac error oldMac["+oldMac+"] newMac["+newMac+"] "+e.getErrorCode());
//			e.printStackTrace();
			if(e.getErrorCode().equals(ErrorEnum.GPRS_NOT_EXISTS.getCode())){
				map.put("error", "Ô­gprs mac²»´æÔÚ");
			}
			map.put("success", false);
			return map;
		}
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteGprs")
	public Map<String,Object> deleteGprs(String gprsMac){
		logger.info("#deleteGprs gprsMac["+gprsMac+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			gprsService.deleteGprs(gprsMac);
		} catch (GreenHouseException e) {
			logger.error("#deleteGprs error gprsMac["+gprsMac+"]"+e.getErrorCode());
			map.put("error", "É¾³ýgprsÊ§°Ü");
			map.put("success", false);
			return map;
		}
		map.put("success", true);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "editGprs")
	public Map<String,Object> editGprs(String oldMac, String newMac,String newName){
		logger.info("#editGprs oldMac["+oldMac+"] newMac["+newMac+"] newName["+newName+"]");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			gprsService.changeGprs(oldMac, newMac, newName);
		} catch (GreenHouseException e) {
			logger.error("#editGprs error oldMac["+oldMac+"] newMac["+newMac+"] newName["+newName+"]"+e.getErrorCode());
			map.put("error", "É¾³ýgprsÊ§°Ü");
			map.put("success", false);
			return map;
		}
		map.put("success", true);
		return map;
	}
}
