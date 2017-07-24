package cn.swust.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(1111);
		Socket socket=server.accept();
		InputStream is=socket.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String s;
		while((s=br.readLine())!=null){
			System.out.println(s);
		}
	}
}
