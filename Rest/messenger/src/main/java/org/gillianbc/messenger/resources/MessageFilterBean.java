package org.gillianbc.messenger.resources;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
	private @QueryParam("year") int year;
	private	@QueryParam("start") int start;
	private	@QueryParam("count") int count;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
