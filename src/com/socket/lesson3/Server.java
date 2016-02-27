package com.socket.lesson3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Study-Java(Android)-Socket-Together
 * step3. 改造部分代码，实现客户端和服务器端每次各自发送一行消息进行通信
 * Server.java:writeUTF()代替write()保证字符编码统一，服务器端增加键盘输入流的包装
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

			Socket soc = serSoc.accept();
			
			DataOutputStream dataOut = new DataOutputStream(soc.getOutputStream());
			DataInputStream dataIn = new DataInputStream(soc.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			String line;

			while(true){
				System.out.println("Server:收到客户端连接");
				line = dataIn.readUTF();
				
				if(line.equalsIgnoreCase("quit")){
					break;
				}	
				System.out.println("Server:客户端发来消息：" + line);
				line = reader.readLine();
				dataOut.writeUTF(line);
				
			}
			dataIn.close();
			dataOut.close();
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
