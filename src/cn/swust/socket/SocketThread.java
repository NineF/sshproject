package cn.swust.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.domain.Location;
import cn.swust.socket.jdbc.DBoperate;
import cn.swust.util.StringUtil;

public class SocketThread implements Runnable {
	private Socket socket;

	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream is = null;
		String sb = "";
		String location = "";

		OutputStream os = null;
		PrintWriter pw = null;
		char c;
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			pw = new PrintWriter(os);

			while ((c = (char) is.read()) != '$') {
				location = location + c;
			}
			location = StringUtil.unicodeToCn(location);
			SocketServer.locations.add(location);

			while (true) {
				sb = "";
				pw.write("^");
				pw.flush();

				while ((c = (char) is.read()) != '$') {
					sb = sb + c;
				}

				String[] data = sb.split("&");
				String temperature = data[0];
				String dampness = data[1];

				double temperature_int = Double.parseDouble(temperature);
				double dampness_int = Double.parseDouble(dampness);
				boolean isOut = false;
				if (temperature_int >= 35 || dampness_int >= 35) {
					isOut = true;
				}

				String locationname = StringUtil.unicodeToCn(data[2]);
				System.out.println(locationname);

				int location_id = DBoperate.selectLocation(locationname);

				Humiture humiture = new Humiture();
				humiture.setTemperature(temperature);
				humiture.setDampness(dampness);
				humiture.setIsOut(isOut);
				DBoperate.insert(humiture, location_id);
				//添加进分表
//				Humiture_1 humiture_1 = new Humiture_1();
//				humiture_1.setTemperature(temperature);
//				humiture_1.setDampness(dampness);
//				humiture_1.setIsOut(isOut);
//				DBoperate.insert(humiture_1, locationname);
//				
				try {
					Thread.sleep(SocketConfig.SOCKET_SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
