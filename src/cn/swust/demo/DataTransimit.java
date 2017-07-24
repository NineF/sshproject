package cn.swust.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import gnu.io.SerialPort;

/*
 * 串口数据发送以及数据传输作为一个类
 * 该类做主要实现对数据包的传输至下单板机
 */
public class DataTransimit {

	/*
	 * 上位机往单板机通过串口发送数据 串口对象 seriesPort 数据帧:dataPackage 发送的标志:数据未发送成功抛出一个异常
	 */
	public static void uartSendDatatoSerialPort(SerialPort serialPort, byte[] dataPackage) {
		OutputStream out = null;
		try {
			out = serialPort.getOutputStream();
			out.write(dataPackage);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭输出流
			if (out != null) {
				try {
					out.close();
					out = null;
					System.out.println("数据已发送完毕!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 上位机接收数据 串口对象seriesPort 接收数据buffer 返回一个byte数组
	 */
	public static byte[] uartReceiveDatafromSingleChipMachine(SerialPort serialPort) {
		byte[] receiveDataPackage = null;
		InputStream in = null;
		try {
			in = serialPort.getInputStream();
			// 获取data buffer数据长度
			int bufferLength = in.available();
			while (bufferLength != 0) {
				receiveDataPackage = new byte[bufferLength];
				in.read(receiveDataPackage);
				bufferLength = in.available();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return receiveDataPackage;
	}
	
	public static void receiveData(SerialPort serialPort){
		InputStream in = null;
		try {
			in = serialPort.getInputStream();
			char c;
			while((c=(char) in.read())!='p'){
				System.out.println(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
