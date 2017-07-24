package cn.swust.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class Humiture {

	private int humitureId;
	
	private String temperature;

	private String dampness;
	
	private Date time;
	
	private boolean isOut;
	
	private Location location;
	

	public int getHumitureId() {
		return humitureId;
	}
	@JSON(serialize=false)
	public Location getLocation() {
		return location;
	}
	
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setHumitureId(int humitureId) {
		this.humitureId = humitureId;
	}


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public boolean getIsOut() {
		return isOut;
	}

	public void setIsOut(boolean isOut) {
		this.isOut = isOut;
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



}
