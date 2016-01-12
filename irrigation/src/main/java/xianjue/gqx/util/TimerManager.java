package xianjue.gqx.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

public class TimerManager {
	
	public static HashMap<String,Timer> timerMap = new HashMap<String, Timer>();

	 //ʱ����
	 private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	 
	 public TimerManager(int hour,int minute,SwitchTask task) {
	  Calendar calendar = Calendar.getInstance(); 
	       
	  /*** ����ÿ��xx:xxִ�з��� ***/ 

	  calendar.set(Calendar.HOUR_OF_DAY, hour);
	  calendar.set(Calendar.MINUTE, minute);
	  calendar.set(Calendar.SECOND, 0);
	  
	  Date date=calendar.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
	  
	  //�����һ��ִ�ж�ʱ�����ʱ�� С�� ��ǰ��ʱ��
	  //��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�
	  if (date.before(new Date())) {
	      date = this.addDay(date, 1);
	  }
	  
	  Timer timer = new Timer();
	  
	  //����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
	  timer.schedule(task,date,PERIOD_DAY);
	  timerMap.put(task.getTaskId()+"", timer);
	 }

	 // ���ӻ��������
	 public Date addDay(Date date, int num) {
	  Calendar startDT = Calendar.getInstance();
	  startDT.setTime(date);
	  startDT.add(Calendar.DAY_OF_MONTH, num);
	  return startDT.getTime();
	 }
	 
	 public static void cancelAll(){
		 Iterator<?> iter = timerMap.entrySet().iterator(); 
		 while (iter.hasNext()) { 
		     Map.Entry entry = (Map.Entry) iter.next(); 
		     Timer t = (Timer) entry.getValue(); 
		     t.cancel();
		     timerMap.remove(t);
		 } 
		 timerMap = new HashMap<String, Timer>();
	 }
	 
	 public static void cancelTask(String key){
		 Timer t = timerMap.get(key);
		 if(t != null){
			 t.cancel();
			 timerMap.remove(key);
		 }
	 }
	}