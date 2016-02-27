package com.socket.lesson5;

import java.io.DataInputStream;
import java.io.IOException;

public class ReceiveThread extends Thread{
private DataInputStream dataIn;
	
	private boolean isRunning;
	private String name;
	
	public ReceiveThread(DataInputStream in,String name){
		this.dataIn = in;
		this.isRunning = true;
		this.name = name;
		System.out.println("["+this.name+"]启动ReceiveThread线程");
	}
	
	public void run(){
		String line;
		while(isRunning){
			try {
				line = this.dataIn.readUTF();
				if(line.equalsIgnoreCase("quit")){
					System.out.println("["+this.name+"]对方要退出了！");
					isRunning = false;
					break;
				}
				System.out.println("["+this.name+"]收到消息:"+line);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("["+this.name+"]结束ReceiveThread线程");
	}
}