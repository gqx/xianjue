package xianjue.gqx.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xianjue.gqx.dao.impl.GprsDao;
import xianjue.gqx.dao.impl.PumpDao;
import xianjue.gqx.dao.impl.ValveDao;
import xianjue.gqx.dao.impl.ZigbeeDao;
import xianjue.gqx.enums.ErrorEnum;
import xianjue.gqx.exception.GreenHouseException;
import xianjue.gqx.po.Gprs;
import xianjue.gqx.po.Zigbee;
import xianjue.gqx.web.service.GprsService;

@Component("gprsService")
public class GprsServiceImpl implements GprsService{

	private static Logger logger = Logger.getLogger(GprsServiceImpl.class);
	
	@Resource
	private GprsDao gprsDao;
	
	@Resource
	private ZigbeeDao zigbeeDao;
	
	@Resource
	private ValveDao valveDao;
	
	@Resource
	private PumpDao pumpDao;
	
	@Transactional
	@Override
	public void createGprs(String gprsMac,String gprsName) throws GreenHouseException {
		logger.info("create gprs start. gprsMac["+gprsMac+"] gprsName["+gprsName+"]");
		if(gprsDao.getByMac(gprsMac) != null){
			logger.error("createGprs error : "+ErrorEnum.GPRS_EXISTS.getDesc()+"gprsMac["+gprsMac+"]");
			throw new GreenHouseException(ErrorEnum.GPRS_EXISTS);
		}
		gprsDao.createGprs(gprsMac, gprsName, null, 0, 0, 0);
		logger.info("create gprs finished. gprsMac["+gprsMac+"] gprsName["+gprsName+"]");
	}

	@Transactional
	@Override
	public void changeGprs(String oldMac, String newMac,String newName) throws GreenHouseException {
		logger.info("changeGprsMac start. oldMac["+oldMac+"] newMac["+newMac+"] newName["+newName+"]");
		Gprs gprs = gprsDao.getByMac(oldMac);
		if(gprs == null){
			logger.error("createGprs error : "+ErrorEnum.GPRS_NOT_EXISTS.getDesc()+"gprsMac["+oldMac+"]");
			throw new GreenHouseException(ErrorEnum.GPRS_NOT_EXISTS);
		}
		gprs.setMac(newMac);
		if(newName != null && newName.trim() != null){
			gprs.setName(newName);
		}
		gprsDao.update(gprs);
		
		List<Zigbee> zigbeeList = zigbeeDao.getZigbeeByGprsMac(oldMac);
		if(zigbeeList != null){
			logger.info("createGprs update zigbee gmac");
			for(Zigbee zigbee : zigbeeList){
				zigbee.setGmac(newMac);
				zigbeeDao.updateZigbee(zigbee);
			}
		}
		
		logger.info("changeGprsMac finished. oldMac["+oldMac+"] newMac["+newMac+"] newName["+newName+"]");
	}

	@Transactional
	@Override
	public List<Gprs> getAllGprs() {
		return gprsDao.getAll();
	}

	@Transactional
	@Override
	public void deleteGprs(String gprsMac) throws GreenHouseException {
		logger.info("deleteGprs start. gprsMac["+gprsMac+"]");
		try{
			
			List<Zigbee> zigbeeList = zigbeeDao.getZigbeeByGprsMac(gprsMac);
			if(zigbeeList != null){
				for(Zigbee zigbee : zigbeeList){
					String zigbeeMac = zigbee.getMac();
					logger.info("deleteGprs deleteZigbee and valve. zigbeeMac["+zigbeeMac+"]");
					zigbeeDao.delateZigbee(zigbeeMac);
					valveDao.deleteByZigbeeMac(zigbeeMac);
					pumpDao.deleteByZigbeeMac(zigbeeMac);
				}
			}
			gprsDao.deleteGprs(gprsMac);
		}catch(Exception e){
			throw new GreenHouseException(ErrorEnum.DB_ERROR);
		}
		
		logger.info("deleteGprs finished. gprsMac["+gprsMac+"]");
	}

}
