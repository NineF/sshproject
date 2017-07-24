package cn.swust.socket;

import cn.swust.util.StringUtil;

public class Data {
	private String temperature;
	private String dampness;
	private String location;

	public Data() {
	}

	public Data(String temperature, String dampness, String location) {
		this.temperature = temperature;
		this.dampness = dampness;
		this.location = location;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDampness() {
		return dampness;
	}

	public void setDampness(String dampness) {
		this.dampness = dampness;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Data [temperature=" + temperature + ", dampness=" + dampness + ", location=" + location + "]";
	}

	public String string() {
		return this.temperature + "&" + this.dampness + "&" + StringUtil.cnToUnicode(this.location) + "$";
	}

}
