package cn.swust.domain;

import java.util.Date;

public class Humiture_1 {

	private int humitureId;
	
	private String temperature;

	private String dampness;
	
	private Date time;
	
	private boolean isOut;
	
	

	public int getHumitureId() {
		return humitureId;
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
