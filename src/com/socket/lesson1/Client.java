package com.socket.lesson1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Study-Java(Android)-Socket-Together 
 * step1. 建立最简单的java Socket 控制台服务器与客户端
 * Client.java:简单的socket监听服务器，监听到客户端的一条消息后立刻关闭
 * */
public class Client {
	// 监听的端口号（0～65535之间）, 为了不与系统占用端口号冲突，通常选择较大一些的端口号
	public static final int PORT = 14000;
	
	public static void main(String[] args) {
		Client c = new Client();
		c.start();
	}
	
	private void start(){
		try {
			Socket soc = new Socket("127.0.0.1",PORT);
			System.out.println("Client:成功连接服务器...");
			
			String line = null;
			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(isr);
			//读取一行系统输入字符串
			line = reader.readLine();
			
			//系统输出流包装socket输出流
			OutputStreamWriter osw = new OutputStreamWriter(soc.getOutputStream());
			BufferedWriter writer = new BufferedWriter(osw);
			
			//向buffer流写入一行数据后立即flush() 然后关闭
			writer.write(line);
			System.out.println("Client 对 Server说:"+line);
			writer.flush();
			writer.close();
			osw.close();
			soc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
