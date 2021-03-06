package xianjue.gqx.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.dao.impl.PumpDao;
import xianjue.gqx.dao.impl.PumpPressureDao;
import xianjue.gqx.dao.impl.ValveDao;
import xianjue.gqx.dao.impl.ZigbeeDao;
import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.enums.ZigbeeTypeEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.po.Pump;
import xianjue.gqx.po.Valve;
import xianjue.gqx.po.Zigbee;
import xianjue.gqx.util.AttributeName;
import xianjue.gqx.web.service.ZigbeeService;

@Component("zigbeeService")
public class ZigbeeServiceImpl implements ZigbeeService{

	private static Logger logger = Logger.getLogger(ZigbeeServiceImpl.class);
	
	@Resource(name="zigbeeDao")
	private ZigbeeDao zigbeeDao;
	
	@Resource
	private GprsDao gprsDao;
	
	@Resource
	private ValveDao valveDao;
	
	@Resource
	private PumpDao pumpDao;
	
	@Resource
	private PumpPressureDao pumpPressureDao;
	
	@Transactional
	@Override
	public void createZigbee(String zigbeeMac,String zigbeeName,String gprsMac,Integer zigbeeType) throws GreenHouseException {
		
		logger.info("#createZigbee# zigbeeMac[" + zigbeeMac + "] zigbeeName[" + zigbeeName + "] gprsMac[" + gprsMac + "] zigbeeType[" + zigbeeType + "]");
		
		if(zigbeeMac == null ||"".equals(zigbeeMac.trim())){
			logger.error("#createZigbee# error : "+ErrorEnum.ZIGBEE_MAC_WRONG.getDesc());
			throw new GreenHouseException(ErrorEnum.ZIGBEE_MAC_WRONG);
		}
		
		if(zigbeeType == null){
			logger.error("#createZigbee# error : "+ErrorEnum.ZIGBEE_TYPE_WRONG.getDesc());
			throw new GreenHouseException(ErrorEnum.ZIGBEE_TYPE_WRONG);
		}
		ZigbeeTypeEnum zType = ZigbeeTypeEnum.getEnum(zigbeeType);
		
		Gprs gprs = gprsDao.getByMac(gprsMac);
		if(gprs == null){
			logger.error("#createZigbee# error : "+ErrorEnum.GPRS_NOT_EXISTS.getDesc());
			throw new GreenHouseException(ErrorEnum.GPRS_NOT_EXISTS);
		}
		
		if(zigbeeDao.getZigbeeByZigbeeMac(zigbeeMac) != null){
			logger.error("#createZigbee# error : "+ErrorEnum.ZIGBEE_EXISTS.getDesc());
			throw new GreenHouseException(ErrorEnum.ZIGBEE_EXISTS);
		}
		
		
		Zigbee zigbee = new Zigbee();
		zigbee.setMac(zigbeeMac);
		zigbee.setName(zigbeeName);
		zigbee.setGmac(gprsMac);
		zigbee.setState(AttributeName.ZIGBEE_INIT);
		zigbee.setZtype(zigbeeType);
		zigbeeDao.createZigbee(zigbee);
		
		if(zType == ZigbeeTypeEnum.VATER_VALVE){
			//先请清除旧的valve
			valveDao.deleteByZigbeeMac(zigbeeMac);
			valveDao.createValve(getNewValve(gprs.getName(),zigbeeMac,zigbeeName,1));
			valveDao.createValve(getNewValve(gprs.getName(),zigbeeMac,zigbeeName,2));
		}else if(zType == ZigbeeTypeEnum.PUMP){
			pumpDao.createPump(getNewPump(gprs.getName(),zigbeeMac,zigbeeName));
		}else{
			logger.error("#createZigbee# error : zigbeeType不匹配!");
		}
		
	}

	@Transactional
	@Override
	public void deleteZigbeeByMac(String zigbeeMac) {
		logger.info("#deleteZigbeeByMac# zigbee mac:" + zigbeeMac);
		zigbeeDao.delateZigbee(zigbeeMac);
		valveDao.deleteByZigbeeMac(zigbeeMac);
		pumpDao.deleteByZigbeeMac(zigbeeMac);
	}


	private Valve getNewValve(String gprsName, String zigbeeMac,String zigbeeName,int zorder){
		Valve valve = new Valve();
		valve.setValve_type("waterValve");
		valve.setZmac(zigbeeMac);
		valve.setZorder(zorder);
		valve.setState(Valve.OFF_STATE);
		valve.setName(gprsName + "-" + zigbeeName + "-" + zorder);
		return valve;
	}
	
	private Pump getNewPump(String gprsName,String zigbeeMac,String zigbeeName){
		Pump pump = new Pump();
		pump.setName(gprsName + "-" + zigbeeName + "-水泵");
		pump.setState(Pump.OFF_STATE);
		pump.setZmac(zigbeeMac);
		return pump;
	}

	@Transactional
	@Override
	public void updateZigbee(String oldMac, String newMac, String name) throws GreenHouseException {
		logger.info("#updateZigbee# oldMac["+oldMac+"] newMac["+newMac+"] name["+name+"]");
		Zigbee zigbee = zigbeeDao.getZigbeeByZigbeeMac(oldMac);
		Gprs gprs = gprsDao.getByMac(zigbee.getGmac());
		if(zigbee != null){
			zigbee.setName(name);
			zigbee.setMac(newMac);
			zigbeeDao.updateZigbee(zigbee);
			
			ZigbeeTypeEnum zigbeeTypeEnum = ZigbeeTypeEnum.getEnum(zigbee.getZtype());
			if(zigbeeTypeEnum == ZigbeeTypeEnum.VATER_VALVE){
				List<Valve> valveList = valveDao.getValveByZigbeeMac(oldMac);
				if(valveList != null){
					for(Valve valve : valveList){
						valve.setName(gprs.getName()+"-"+name+"-"+valve.getZorder());
						valveDao.updateValve(valve);
					}
				}
			}else if(zigbeeTypeEnum == ZigbeeTypeEnum.PUMP){
				List<Pump> pumpList = pumpDao.getPumpByZigbeeMac(oldMac);
				if(pumpList != null){
					for(Pump pump : pumpList){
						pump.setName(gprs.getName()+"-"+name+"-水泵");
						pumpDao.update(pump);
				}
			}
		}
			
			if(!oldMac.equals(newMac)){
				//update device if zigbee mac is changed
				if(zigbeeTypeEnum == ZigbeeTypeEnum.VATER_VALVE){
					logger.info("#updateZigbee# update valve zmac");
					valveDao.changeValveByZigbeeMac(oldMac, newMac);
				}else if(zigbeeTypeEnum == ZigbeeTypeEnum.PUMP){
					logger.info("#updateZigbee# update pump zmac");
					pumpDao.changePumpByZigbeeMac(oldMac,newMac);
				}else{
					logger.warn("#updateZigbee# zigbee has no devices!");
				}
			}

		}else{
			logger.error("#updateZigbee# error : "+ErrorEnum.ZIGBEE_NOT_EXISTS.getDesc());
			throw new GreenHouseException(ErrorEnum.ZIGBEE_NOT_EXISTS);
		}
	}
	@Transactional
	@Override
	public List<Zigbee> getZigbeeByGprsAndType(String gprsMac, Integer zigbeeType) throws GreenHouseException {
		if(zigbeeType == null){
			logger.error("#createZigbee# error : "+ErrorEnum.ZIGBEE_TYPE_WRONG.getDesc());
			throw new GreenHouseException(ErrorEnum.ZIGBEE_TYPE_WRONG);
		}
		List<Zigbee> list= zigbeeDao.getZigbeeByGprsMacAndType(gprsMac, zigbeeType);
		if(list != null){
			logger.info("getZigbeeByGprsAndType zigbee list size:"+list.size());
		}else{
			logger.info("getZigbeeByGprsAndType zigbee list is null");
		}
		
		return list;
	}
}
