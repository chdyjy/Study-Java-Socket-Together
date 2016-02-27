package com.socket.lesson4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

public static final int PORT = 14000;
	
	public static void main(String[] args) {
		Client c = new Client();
		c.start();
	}
	
	private void start(){
		try {
			Socket soc = new Socket("127.0.0.1",PORT);
			System.out.println("[Client]:成功连接服务器...");
			
			OutputStream out = soc.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);
			
			InputStream in = soc.getInputStream();
			DataInputStream dataIn = new DataInputStream(in);
			
			new ClientReceiveThread(dataIn).start();
			new ClientSendThread(dataOut).start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
