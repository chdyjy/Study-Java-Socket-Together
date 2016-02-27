package com.socket.lesson5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static final int PORT = 14000;
	private String name;
	private Socket soc;
	
	public Client(){
		this.name = "Client";
		try {
			soc = new Socket("127.0.0.1",PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[Client]:成功连接服务器...");
	}
	
	public static void main(String[] args) {
		Client c = new Client();
		c.start();
	}
	
	
	private void start(){
		try {
			OutputStream out = soc.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);
			
			InputStream in = soc.getInputStream();
			DataInputStream dataIn = new DataInputStream(in);
			
			new ReceiveThread(dataIn,this.name).start();
			new SendThread(dataOut,this.name).start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
