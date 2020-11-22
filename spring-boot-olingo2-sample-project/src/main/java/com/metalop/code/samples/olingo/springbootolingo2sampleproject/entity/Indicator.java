package com.metalop.code.samples.olingo.springbootolingo2sampleproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Indicator {

	@Column
	String deviceId;

	@Id
	@Column
	String indicatorId;

	@Column
	String IndicatorName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(String indicatorId) {
		this.indicatorId = indicatorId;
	}

	public String getIndicatorName() {
		return IndicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		IndicatorName = indicatorName;
	}

}
