package com.socket.lesson2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Study-Java(Android)-Socket-Together
 * step1. 增加**服务器向客户端发出反馈信息**的功能
 * Server.java:收到客户端的消息，立即把此消息转发回客户端
 * */
public class Server {
	// 监听的端口号（0～65535之间）, 为了不与系统占用端口号冲突，通常选择较大一些的端口号
	public static final int PORT = 14000;

	// 类的构造函数
	Server() {
		// 打印控制台语句作为记号
		System.out.println("服务器启动...");
	}

	public static void main(String[] args) {

		Server s = new Server();
		s.start();
	}

	private void start() {
		try {
			ServerSocket serSoc = new ServerSocket(PORT);
			Socket soc = null;
			soc = serSoc.accept();
			String line;


			while(true){
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	
				line = in.readLine();
				if(line.equalsIgnoreCase("quit")){
					break;
				}	
				System.out.println("Server:客户端发来消息：" + line);
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
				out.write(line+"\n");
				out.flush();
		        //in.close();
			}
			
			// socket关闭
			soc.close();
			System.out.println("Server:socket关闭");
			serSoc.close();
			System.out.println("Server:socketServer关闭");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
}