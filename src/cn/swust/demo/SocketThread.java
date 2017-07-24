package cn.swust.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import cn.swust.socket.SocketManager;

public class SocketThread implements Runnable {
	private Socket socket;

	public SocketThread(Socket socket) {
		this.socket = socket;
		SocketManager.getManager().addSocket(this.socket);
	}

	@Override
	public void run() {
		InputStream is = null;
		StringBuilder sb=new StringBuilder();
		
		OutputStream os=null;
		PrintWriter pw=null;
		try {
			os=this.socket.getOutputStream();
			pw=new PrintWriter(os);
			pw.write("^");
			pw.flush();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			is = this.socket.getInputStream();
			
			char c;
			while((c=(char) is.read())!='$'){
				sb.append(c);
			}
		System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
