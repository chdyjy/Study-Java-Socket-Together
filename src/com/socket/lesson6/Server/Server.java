package com.socket.lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	// 监听的端口号（0～65535之间）, 为了不与系统占用端口号冲突，通常选择较大一些的端口号
	public static final int PORT = 14000;
	private String name;
	private ServerSocket sc;
	private Socket soc;
	// 类的构造函数
	Server() {
		this.name = "Server";
		// 打印控制台语句作为记号
		try {
			this.sc = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("服务器启动...");
	}

	public static void main(String[] args) {

		Server s = new Server();
		s.start();
	}

	private void start() {
		try {
			soc = sc.accept();
			
			DataOutputStream dataOut = new DataOutputStream(soc.getOutputStream());
			DataInputStream dataIn = new DataInputStream(soc.getInputStream());
			
			new ReceiveThread(dataIn,this.name).start();
			new SendThread(dataOut,this.name).start();
			System.out.println("[Server]线程飞起！");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
}
