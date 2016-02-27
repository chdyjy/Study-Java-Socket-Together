package com.socket.lesson2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Study-Java(Android)-Socket-Together
 * step2. 增加**服务器向客户端发出反馈信息**的功能
 * Client.java:使用while循环不断向服务器端发送消息
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
			//Buffered 包装输入输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
			BufferedReader ret = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			
			String line = null;
			String retLine = null;
			while(true){
				line = reader.readLine();
				System.out.println("Client 对 Server说:"+line);
								
				writer.write(line+"\n");
				writer.flush();	
				if(line.equalsIgnoreCase("quit")){
					break;
				}
				
				retLine = ret.readLine();
				System.out.println("Server 对 Client说:"+retLine);
			}
			System.out.println("client：退出Socket会话");
			
			soc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
