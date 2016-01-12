package xianjue.gqx.web.service.impl;

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
	public void createZigbee(String zigbeeMac,String zigbeeName,String gprsMac,int zigbeeType) throws GreenHouseException {
		ZigbeeTypeEnum zType = ZigbeeTypeEnum.getEnum(zigbeeType);
		logger.info("create zigbee: zigbeeMac["+zigbeeMac+"] zigbeeName["+zigbeeName+"] gprsMac["+gprsMac+"] zigbeeType["+zType+"]");
		
		Gprs gprs = gprsDao.getByMac(gprsMac);
		if(gprs == null){
			logger.error("createZigbee error : "+ErrorEnum.GPRS_NOT_EXISTS.getDesc());
			throw new GreenHouseException(ErrorEnum.GPRS_NOT_EXISTS);
		}
		
		if(zigbeeDao.getZigbeeByZigbeeMac(zigbeeMac) != null){
			logger.error("createZigbee error : "+ErrorEnum.ZIGBEE_EXISTS.getDesc());
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
			logger.error("createZigbee error : zigbeeType不匹配!");
		}
		
	}

	@Transactional
	@Override
	public void deleteZigbeeByMac(String zigbeeMac) {
		logger.info("deleteZigbeeByMac zigbee mac:"+zigbeeMac);
		zigbeeDao.delateZigbee(zigbeeMac);
		valveDao.deleteByZigbeeMac(zigbeeMac);
	}
	
	private Valve getNewValve(String gprsName, String zigbeeMac,String zigbeeName,int zorder){
		Valve valve = new Valve();
		valve.setValve_type("waterValve");
		valve.setZmac(zigbeeMac);
		valve.setZorder(zorder);
		valve.setState(Valve.OFF_STATE);
		valve.setName(gprsName+"-"+zigbeeName+"-"+zorder);
		return valve;
	}
	
	private Pump getNewPump(String gprsName,String zigbeeMac,String zigbeeName){
		Pump pump = new Pump();
		pump.setName(gprsName+"-"+zigbeeName+"-水泵");
		pump.setState(Pump.OFF_STATE);
		pump.setZmac(zigbeeMac);
		return pump;
	}
	

	@Transactional
	@Override
	public void updateZigbeeName(String zmac, String name) throws GreenHouseException {
		Zigbee zigbee = zigbeeDao.getZigbeeByZigbeeMac(zmac);
		if(zigbee != null){
			zigbee.setName(name);
			zigbeeDao.updateZigbee(zigbee);
		}else{
			throw new GreenHouseException(ErrorEnum.ZIGBEE_NOT_EXISTS);
		}
		
	}

	@Transactional
	@Override
	public void changeZigbeeMac(String oldMac, String newMac) throws GreenHouseException {
		Zigbee zigbee = zigbeeDao.getZigbeeByZigbeeMac(oldMac);
		if(zigbee != null){
			zigbee.setGmac(newMac);
			zigbeeDao.updateZigbee(zigbee);
			valveDao.changeValveByZigbeeMac(oldMac, newMac);
		}else{
			throw new GreenHouseException(ErrorEnum.ZIGBEE_NOT_EXISTS);
		}
		
	}

}
