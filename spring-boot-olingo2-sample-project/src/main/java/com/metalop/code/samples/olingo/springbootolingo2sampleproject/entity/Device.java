package com.metalop.code.samples.olingo.springbootolingo2sampleproject.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Device {

	@Id
	@Column
	String deviceId;

	@Column
	String deviceName;

	@OneToMany
	@JoinColumn(name = "deviceId", nullable = true)
	Set<Indicator> indicators;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Set<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(Set<Indicator> indicators) {
		this.indicators = indicators;
	}

}
