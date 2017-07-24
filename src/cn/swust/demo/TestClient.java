package cn.swust.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket client = new Socket("127.0.0.1", 1111);
		OutputStream os = client.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		pw.write(str);
		pw.flush();
		client.shutdownOutput();
	}
}
