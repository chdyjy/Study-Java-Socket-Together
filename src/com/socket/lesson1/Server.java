package com.socket.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Study-Java(Android)-Socket-Together
 * step1. 建立最简单的java Socket 控制台服务器与客户端
 * Server.java:简单的socket监听服务器，监听到客户端的一条消息后立刻关闭
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
			// 使用accept()阻塞，等待客户请求
			soc = serSoc.accept();
			System.out.println("Server:收到客户端连接");
			String line;
			/*
			 * 使用新建立的socket对象的getIntutStream()方法获取收到的输入流， 由于返回值是"input stream"
			 * ，所以我们用InputStreamReader来包装。
			 */
			InputStreamReader isr = new InputStreamReader(soc.getInputStream());
			//BufferedReader 缓存区的使用可以减少IO操作，提高系统效率
			BufferedReader bf = new BufferedReader(isr);
			line = bf.readLine();
			System.out.println("Server:客户端发来消息："+line);
			//socket关闭
			soc.close();
			System.out.println("Server:socket关闭");
			serSoc.close();
			System.out.println("Server:socketServer关闭");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
