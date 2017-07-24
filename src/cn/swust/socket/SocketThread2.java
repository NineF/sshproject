package cn.swust.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SocketThread2 implements Runnable {

	@Override
	public void run() {
		while (true) {
			ArrayList<Socket> sockets = SocketManager.getManager().getSockets();
			for (Socket socket : sockets) {
				InputStream is = null;
				StringBuilder sb = new StringBuilder();
				StringBuilder location=new StringBuilder();

				OutputStream os = null;
				PrintWriter pw = null;
				char c;
				try {
					is = socket.getInputStream();
					while ((c = (char) is.read()) != '$') {
						location.append(c);
					}
					SocketServer.locations.add(location.toString());
					
					os = socket.getOutputStream();
					pw = new PrintWriter(os);
					pw.write("^");
					pw.flush();

					while ((c = (char) is.read()) != '$') {
						sb.append(c);
					}
					System.out.println(sb.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(SocketConfig.SOCKET_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
