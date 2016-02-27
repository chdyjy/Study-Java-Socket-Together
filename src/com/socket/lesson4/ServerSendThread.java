/**
 * 
 */
package com.socket.lesson4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Study-Java(Android)-Socket-Together
 * step4. 对程序进行多线程改造，使得客户端和服务器可以自由发送消息
 * ServerSendThread.java:服务器发送通信数据线程
 * */
public class ServerSendThread extends Thread{
	private DataOutputStream dataOut;
	
	private boolean isRunning;
	
	public ServerSendThread(DataOutputStream out){
		this.dataOut = out;
		isRunning = true;
		System.out.println("[Server]启动ServerSendThread线程");
	}
	
	public void run(){
		BufferedReader dataReader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while(isRunning){
			try {
				line = dataReader.readLine();
				this.dataOut.writeUTF(line);
				if(line.equalsIgnoreCase("quit")){
					System.out.println("[Server]Server 要退出了！");
					isRunning = false;
					break;
				}
				System.out.println("[Server]Server 对 Client 说:"+line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[Server]结束ServerSendThread线程");
	}
}
