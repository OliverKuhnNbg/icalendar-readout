package de.twist.icalendarreadout.models;

import java.util.Date;

public class GameData {

	private Date dtStamp; 
	private Date dtStart;
	private Date dtEnd;
	private Game summery;
	private Long uid;
	
	public GameData() {
	}

	
	public Date getDtStamp() {
		return dtStamp;
	}

	public Date getDtStart() {
		return dtStart;
	}

	public Date getDtEnd() {
		return dtEnd;
	}

	public Game getSummery() {
		return summery;
	}

	public Long getUid() {
		return uid;
	}

	
	public void setDtStamp(Date dtStamp) {
		this.dtStamp = dtStamp;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

	public void setSummery(Game summery) {
		this.summery = summery;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
}
