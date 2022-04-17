package com.metalop.code.samples.olingo.springbootolingo2sampleproject.utils.custom_adapters;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.persistence.jaxb.xmlmodel.XmlJavaTypeAdapter;

public class LocalDateTimeXmlAdapter extends XmlAdapter<Timestamp,Object> {

	@Override
	public Object unmarshal(Timestamp v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp marshal(Object v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	
}
