package xianjue.gqx.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.web.service.TaskScheduleService;

@Controller
@RequestMapping(value = "/taskSchedule/")
public class TaskScheduleController {
	private static Logger logger = Logger.getLogger(ZigbeeManageController.class);
	
	@Resource(name="taskScheduleService")
	private TaskScheduleService taskScheduleService;
	
	@RequestMapping(value = "index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("taskSchedule");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "startAutoTask")
	public Map<String,Object> startAutoTask(){
		logger.info("#startAutoTask#");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			taskScheduleService.startAutoTask();
			map.put("success", true);
			return map;
		} catch (GreenHouseException e) {
			e.printStackTrace();
			map.put("error", e.getErrorDesc());
			map.put("success", false);
			return map;
		}		
	}
	
	@ResponseBody
	@RequestMapping(value = "stopAutoTask")
	public Map<String,Object> stopAutoTask(){
		logger.info("#stopAutoTask#");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			taskScheduleService.stopAutoTask();
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e.getMessage());
			map.put("success", false);
			return map;
		}		
	}
}
