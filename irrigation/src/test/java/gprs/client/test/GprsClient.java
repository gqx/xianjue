package gprs.client.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GprsClient {
	private Socket client;

	public void run(){  
        OutputStream os = null;  
        InputStream is = null;  
        try {  
//            client = new Socket("115.28.85.14", 2020);
//            client = new Socket("115.126.38.13", 2020);
        	client = new Socket("localhost", 2020);
            os = client.getOutputStream();//  
            System.out.println("正在发送信息...");  
           
            byte[] b = getTestBytesD202();
            
            System.out.println(Integer.toHexString(b[0]));
            os.write(b);//发送信息  
            os.flush();//注意:如果不使用flush方法,服务端将收到客户端发送的信息  
            is = client.getInputStream();//  
            byte[] recieve = new byte[1024];//缓冲区  
            System.out.println("正在接收回复信息...");  
            int length = is.read(recieve);//读取信息  
            System.out.println("服务器返回的信息: "+new String(recieve, 0, length));  
            System.out.println("接收回复信息完成"); 
            System.out.println(bytesToHexString(b));
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            //所有服务  
            try {  
                os.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}
	
	public static void main(String args[]){
		GprsClient g = new GprsClient();
		g.run();
	}
	
	private byte FCS(byte[] b, int msgPtr, int len) {
		byte xorResult = 0;
		for (; msgPtr <= len; msgPtr++) {
			xorResult = (byte) (xorResult ^ b[msgPtr]);
		}

		return xorResult;
	}
	
	private byte[] getTestBytesD1(){
		 byte[] b = new byte[20];
		 //帧头
         b[0] = (byte) 0xFE;
         //数据长度
         b[1] = (byte) 0x01;
         b[2] = (byte) 0x00;
         //命令
         b[3] = (byte) 0xD1;
         //gprs mac
         b[4] = (byte) 0x01;
         b[5] = (byte) 0x02;
         b[6] = (byte) 0x03;
         b[7] = (byte) 0x04;
         b[8] = (byte) 0x05;
         b[9] = (byte) 0x06;
         b[10] = (byte) 0x07;
         b[11] = (byte) 0x08;
         b[12] = (byte) 0x09;
         b[13] = (byte) 0x0A;
         b[14] = (byte) 0x0B;
         b[15] = (byte) 0x0C;
         b[16] = (byte) 0x0D;
         b[17] = (byte) 0x0E;
         b[18] = (byte) 0x0F;
         //校验
         b[19] = FCS(b, 1, 18);
         
         return b;
	}
	
	private byte[] getTestBytesD2(){
		 byte[] b = new byte[32];
		 //帧头
        b[0] = (byte) 0xFE;
        b[1] = (byte) 0x01;
        //type
        b[2] = (byte) 0x01;
        //命令
        b[3] = (byte) 0xD2;
        //zigbee mac
        b[4] = (byte) 0x03;
        b[5] = (byte) 0x02;
        b[6] = (byte) 0x03;
        b[7] = (byte) 0x04;
        b[8] = (byte) 0x05;
        b[9] = (byte) 0x06;
        b[10] = (byte) 0x07;
        b[11] = (byte) 0x08;
        
        b[12] = (byte) 0x01;
        b[13] = (byte) 0x01;
        b[14] = (byte) 0x01;
        b[15] = (byte) 0x01;
        //gprs mac
        b[16] = (byte) 0x02;
        b[17] = (byte) 0x02;
        b[18] = (byte) 0x03;
        b[19] = (byte) 0x04;
        b[20] = (byte) 0x05;
        b[21] = (byte) 0x06;
        b[22] = (byte) 0x07;
        b[23] = (byte) 0x08;
        b[24] = (byte) 0x09;
        b[25] = (byte) 0x0A;
        b[26] = (byte) 0x0B;
        b[27] = (byte) 0x0C;
        b[28] = (byte) 0x0D;
        b[29] = (byte) 0x0E;
        b[30] = (byte) 0x0F;
         
        b[31] = FCS(b, 1, 30);
        
        return b;
	}
	
	private byte[] getTestBytesD202(){
		 byte[] b = new byte[24];
		 //帧头
       b[0] = (byte) 0xFE;
       b[1] = (byte) 0x01;
       //type
       b[2] = (byte) 0x02;
       //命令
       b[3] = (byte) 0xD2;
     //gprs mac
       b[4] = (byte) 0x02;
       b[5] = (byte) 0x02;
       b[6] = (byte) 0x03;
       b[7] = (byte) 0x04;
       b[8] = (byte) 0x05;
       b[9] = (byte) 0x06;
       b[10] = (byte) 0x07;
       b[11] = (byte) 0x08;
       b[12] = (byte) 0x09;
       b[13] = (byte) 0x0A;
       b[14] = (byte) 0x0B;
       b[15] = (byte) 0x0C;
       b[16] = (byte) 0x0D;
       b[17] = (byte) 0x0E;
       b[18] = (byte) 0x0F;
       
       b[19] = (byte) 0x01;
       b[20] = (byte) 0x19;
       b[21] = (byte) 0x01;
       b[22] = (byte) 0x01;
        
       b[23] = FCS(b, 1, 22);
       
       return b;
	}

	private byte[] getTestBytesD3(){
		 byte[] b = new byte[24];
		 //帧头
         b[0] = (byte) 0xFE;
         //数据长度
         b[1] = (byte) 0x01;
         b[2] = (byte) 0x00;
         //命令
         b[3] = (byte) 0xD3;
         //gprs mac
         b[4] = (byte) 0x01;
         b[5] = (byte) 0x02;
         b[6] = (byte) 0x03;
         b[7] = (byte) 0x04;
         b[8] = (byte) 0x05;
         b[9] = (byte) 0x06;
         b[10] = (byte) 0x07;
         b[11] = (byte) 0x08;
         b[12] = (byte) 0x09;
         b[13] = (byte) 0x0A;
         b[14] = (byte) 0x0B;
         b[15] = (byte) 0x0C;
         b[16] = (byte) 0x0D;
         b[17] = (byte) 0x0E;
         b[18] = (byte) 0x0F;
         
         b[19] = (byte) 0x01;
         b[20] = (byte) 0x02;
         b[21] = (byte) 0x03;
         b[22] = (byte) 0x04;
         //校验
         b[23] = FCS(b, 1, 22);
         
         return b;
	}
	
	private byte[] getTestBytesD4(){
		 byte[] b = new byte[33];
		 //帧头
        b[0] = (byte) 0xFE;
        //数据长度
        b[1] = (byte) 0x01;
        b[2] = (byte) 0x00;
        //命令
        b[3] = (byte) 0xD4;
        //gprs mac
        b[4] = (byte) 0x01;
        b[5] = (byte) 0x02;
        b[6] = (byte) 0x03;
        b[7] = (byte) 0x04;
        b[8] = (byte) 0x05;
        b[9] = (byte) 0x06;
        b[10] = (byte) 0x07;
        b[11] = (byte) 0x08;
        b[12] = (byte) 0x09;
        b[13] = (byte) 0x0A;
        b[14] = (byte) 0x0B;
        b[15] = (byte) 0x0C;
        b[16] = (byte) 0x0D;
        b[17] = (byte) 0x0E;
        b[18] = (byte) 0x0F;
      //zigbee mac
        b[19] = (byte) 0x05;
        b[20] = (byte) 0x02;
        b[21] = (byte) 0x03;
        b[22] = (byte) 0x04;
        b[23] = (byte) 0x05;
        b[24] = (byte) 0x06;
        b[25] = (byte) 0x07;
        b[26] = (byte) 0x08;
       
        
        b[27] = (byte) 0x03;
        b[28] = (byte) 0x06;
        b[29] = (byte) 0x03;
        b[30] = (byte) 0x03;
        b[31] = (byte) 0x03;
        
        //校验
        b[32] = FCS(b, 1, 31);
        
        return b;
	}
}
