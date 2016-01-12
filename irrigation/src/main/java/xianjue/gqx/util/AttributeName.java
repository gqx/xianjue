package xianjue.gqx.util;

public class AttributeName {
	public static final byte GPRS_START = (byte) 0xD1; // ����gprs����ʱ������
	public static final byte ZIGBEE_START = (byte) 0xD2; // ����zigbee���Ӷˣ�����ʱ������
	public static final byte FROM_MAINZIGBEE = (byte) 0xD3; // ����zigbee���ܶˣ��ϴ�������
	public static final byte PRESSURE_ZIGBEE = (byte) 0xD4; // ����zigbee���Ӷˣ�ˮ��״̬���󱨾�����
	public static final byte TO_GPRS_CMD = (byte) 0xD5; // ������gprs���ſ�������
	public static final byte ZIGBEE_RESPONSE = (byte) 0xD6; // zigbee����

	public static final byte TO_GPRS_LIGHT = (byte)0x00;//�ƿ�����
	public static final byte TO_GPRS_WATER_VALVE = (byte)0x01;//ˮ����������
	public static final byte TO_GPRS_ELECTRIC_MACHINE = (byte)0x02;//�����������
	
	public static final byte ZIGBEE_TYPE_SWITCH_FLAG = (byte) 0x00; // zigbee���ͣ���ͨ����
	public static final byte ZIGBEE_TYPE_WATER_PUMP_FLAG = (byte) 0x01;// zigbee���ͣ�ˮ��
	public static final byte ZIGBEE_TYPE_PRESSURE_SENSOR_FLAG = (byte) 0x02;// zigbee���ͣ�ѹ��������

	public static final int ZIGBEE_TYPE_SWITCH = 0;
	public static final int ZIGBEE_TYPE_WATER_PUMP = 1;
	public static final int ZIGBEE_TYPE_PRESSURE_SENSOR = 2;

	public static String newGprsName;
	public static String newZigbeeName;

	public static final byte STAR_TFLAG = (byte) 0xfe;
	public static final byte TOGPRS_LENGTH = (byte) 0x0c;
	public static final byte SWITCH_ON_CMD = (byte) 0x01;
	public static final byte SWITCH_OFF_CMD = (byte) 0x00;
	public static final byte SWITCH_DO_NOTHING = (byte) 0x03;

	public static final String TURNGROUP_FILE_NAME = "turngroup.xml";
	public static final String TURNTASK_FILE_NAME = "turntask.xml";

	public static final int TASK_TO_RUN_TOKEN = 0;
	public static final int TASK_SHOULD_RUN_TOKEN = 1;
	public static final int TASK_SHOULD_FINISHED_TOKEN = -1;

	public static final int SWITCH_ON_STATE = 1;
	public static final int SWITCH_OFF_STATE = 0;

	public static byte[] gorder = {(byte) 0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01,(byte)0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01};
	
	public static int DEVICE_ON = 1;
	public static int DEVICE_OFF = 0;
	
	public static int E_MACHINE_FORWARD = 1;
	public static int E_MACHINE_BACKWARD = -1;
	public static int E_MACHINE_STOP = 0;
	
	public static String SCHEDULE_TYPE_LIGHT = "light";
	public static String SCHEDULE_TYPE_VALVE = "valve";
	
	public static final String ZIGBEE_INIT = "init";
	public static final String ZIGBEE_CONNECTED = "connected";
	public static final String GPRS_INIT = "init";
	public static final String GPRS_CONNECTED = "connected";
}
