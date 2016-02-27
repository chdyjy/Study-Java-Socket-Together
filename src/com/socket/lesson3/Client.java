package com.socket.lesson3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Study-Java(Android)-Socket-Together
 * step3. 改造部分代码，实现客户端和服务器端每次各自发送一行消息进行通信
 * Client.java:writeUTF()代替write()保证字符编码统一
 * */

public class Client {

public static final int PORT = 14000;
	
	public static void main(String[] args) {
		Client c = new Client();
		c.start();
	}
	
	private void start(){
		try {
			Socket soc = new Socket("127.0.0.1",PORT);
			System.out.println("Client:成功连接服务器...");
			
			OutputStream out = soc.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);
			
			InputStream in = soc.getInputStream();
			DataInputStream dataIn = new DataInputStream(in);
			
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader dataReader = new BufferedReader(reader);
			
			String line = null;
			
			while(true){
				line = dataReader.readLine();
				dataOut.writeUTF(line);
				
				if(line.equalsIgnoreCase("quit")){
					break;
				}
				System.out.println("Client 对 Server说:"+line);
				
				line = dataIn.readUTF();
				System.out.println("Server 对 Client说:"+line);
				
			}
			System.out.println("client：退出Socket会话");
			dataOut.close();
			dataIn.close();
			soc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
