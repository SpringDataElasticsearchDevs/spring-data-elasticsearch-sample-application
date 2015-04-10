package org.springframework.data.elasticsearch.entities;

/**
 * Created by mohsinhusen on 10/04/15.
 */
public class Sector {

	 private int id;
	private String sectorName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
}
