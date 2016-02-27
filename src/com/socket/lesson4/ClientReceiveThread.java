package com.socket.lesson4;

import java.io.DataInputStream;
import java.io.IOException;

/*
 * Study-Java(Android)-Socket-Together
 * step4. 对程序进行多线程改造，使得客户端和服务器可以自由发送消息
 * ClientReceiveThread.java:客户端收取通信数据线程
 * */
public class ClientReceiveThread extends Thread{
	private DataInputStream dataIn;
	private boolean isRunning;
	
	public ClientReceiveThread(DataInputStream in){
		this.dataIn = in;
		isRunning = true;
		System.out.println("[Client]启动ClientReceiveThread线程");
	}
	
	public void run(){
		String line;
		while(isRunning){
			try {
				line = this.dataIn.readUTF();
				if(line.equalsIgnoreCase("quit")){
					System.out.println("[Client]Server 要退出了！");
					isRunning = false;
					break;
				}
				System.out.println("[Client]Server 对 Client 说:"+line);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[Client]结束ServerReceiveThread线程");
	}
}
