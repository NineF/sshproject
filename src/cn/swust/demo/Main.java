package cn.swust.demo;

import java.util.ArrayList;
import java.util.Arrays;

import gnu.io.SerialPort;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> arraylist = SocketUtil.uartPortUseAblefind();
		int useAbleLen = arraylist.size();
		if (useAbleLen == 0) {
			System.out.println("没有找到可用的串口端口，请check设备！");
		} else {
			System.out.println("已查询到该计算机上有以下端口可以使用：");
			System.out.println("该COM端口名称:" + arraylist.get(0));
			// 测试串口配置的相关方法

			// 取出第一个COM端口进行测试
			SerialPort serialPort = SocketUtil.portParameterOpen(arraylist.get(0), 9600);
			// String currentDateTime = DateUtil.getCurrentDateTime();
			// System.out.println(currentDateTime);
			// byte[] bytes = DateUtil.dateTimeBytesGet(currentDateTime);
			// // System.out.println(Arrays.toString(bytes));
			//// String str = DateUtil.dateTimeBytesfromTostring(bytes);
			//// System.out.println(str);
			// byte[] terimalTimeByte =
			// DateUtil.makeCurrentDateTimefromStringtoFramePackage(bytes);
			// System.out.println(Arrays.toString(terimalTimeByte));
			// DataTransimit.uartSendDatatoSerialPort(serialPort,
			// terimalTimeByte);
			//
			// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
//			byte[] receivebyte;
//			while (true) {
//				receivebyte = DataTransimit.uartReceiveDatafromSingleChipMachine(serialPort);
//				if (receivebyte != null)
//					break;
//			}
//			System.out.println(Arrays.toString(receivebyte));
			DataTransimit.receiveData(serialPort);
			serialPort.close();
		}
	}

}
