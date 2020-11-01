package com.metalop.code.samples.olingo.springbootolingo2sampleproject.entity;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Student {

	@Id
	@Column
	String studentID;

	@Column
	String name;

	@Column
	int marks;

	@Column(nullable = false)
	@Lob
	private byte[] picture;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String longTextDescription;

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getLongTextDescription() {
		return longTextDescription;
	}

	public void setLongTextDescription(String longTextDescription) {
		this.longTextDescription = longTextDescription;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return String.format("Student [studentID=%s, name=%s, marks=%s, picture=%s, longTextDescription=%s]", studentID,
				name, marks, Arrays.toString(picture), longTextDescription);
	}

}
