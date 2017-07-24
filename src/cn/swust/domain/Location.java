package cn.swust.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Location {

	private int locationid;
	private String locationname;

	private Set<Humiture> humitures = new HashSet<>();

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}



	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	@JSON(serialize=false)
	public Set<Humiture> getHumitures() {
		return humitures;
	}
	
	public void setHumitures(Set<Humiture> humitures) {
		this.humitures = humitures;
	}

}
