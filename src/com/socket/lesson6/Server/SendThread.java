package com.socket.lesson6.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SendThread extends Thread{
	private DataOutputStream dataOut;
	
	private boolean isRunning;
	private String name;
	
	public SendThread(DataOutputStream out,String name){
		this.dataOut = out;
		this.isRunning = true;
		this.name = name;
		System.out.println("["+this.name+"]启动SendThread线程");
	}
	
	public void run(){
		BufferedReader dataReader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while(isRunning){
			try {
				line = dataReader.readLine();
				this.dataOut.writeUTF(line);
				if(line.equalsIgnoreCase("quit")){
					System.out.println("["+this.name+"]对方要退出了！");
					isRunning = false;
					break;
				}
				System.out.println("["+this.name+"]发送消息:"+line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("["+this.name+"]结束SendThread线程");
	}
}
