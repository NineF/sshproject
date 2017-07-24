package cn.swust.socket;

import java.net.Socket;
import java.util.ArrayList;

public class SocketManager {
	public static ArrayList<String> array=new ArrayList<>();
	private static ArrayList<Socket> sockets;
	
	public static ArrayList<Thread> threads=new ArrayList<>();
	
	private static SocketManager single=new SocketManager();
	
	private SocketManager(){
		sockets=new ArrayList<>();
	}
	
	public static SocketManager getManager(){
		return single;
	}
	
	public void addSocket(Socket socket){
		sockets.add(socket);
	}
	
	public void removeSocket(Socket socket){
		sockets.remove(socket);
	}
	
	public ArrayList<Socket> getSockets(){
		return sockets;
	}

	public static ArrayList<Thread> getThreads() {
		return threads;
	}

	public static void setThreads(ArrayList<Thread> threads) {
		SocketManager.threads = threads;
	}
	
}
