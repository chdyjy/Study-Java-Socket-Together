package com.socket.lesson4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClientSendThread extends Thread{
	private DataOutputStream dataOut;
	
	private boolean isRunning;
	
	public ClientSendThread(DataOutputStream out){
		this.dataOut = out;
		isRunning = true;
		System.out.println("[Client]启动ClientSendThread线程");
	}
	
	public void run(){
		BufferedReader dataReader = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while(isRunning){
			try {
				line = dataReader.readLine();
				this.dataOut.writeUTF(line);
				if(line.equalsIgnoreCase("quit")){
					System.out.println("[Client]Client 要退出了！");
					isRunning = false;
					break;
				}
				System.out.println("[Client]Client 对 Server 说:"+line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[Client]结束ClientSendThread线程");
		
	}
}
