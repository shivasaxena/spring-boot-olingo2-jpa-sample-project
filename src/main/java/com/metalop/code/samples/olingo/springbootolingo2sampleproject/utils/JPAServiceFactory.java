package com.metalop.code.samples.olingo.springbootolingo2sampleproject.utils;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAServiceFactory extends ODataJPAServiceFactory {

	private static final String PERSISTENT_UNIT = "detault";
	private static final String EMF = "entityManagerFactory";

	private static final Logger LOG = LoggerFactory.getLogger(JPAServiceFactory.class);
	private ODataJPAContext oDataJPAContext;

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDataJPACtx = getODataJPAContext();
		EntityManagerFactory emf = (EntityManagerFactory) SpringContextUtil.getBean(EMF);
		LOG.debug("EMF in JPAservicefactory " + emf);
		oDataJPACtx.setEntityManagerFactory(emf);
		oDataJPACtx.setPersistenceUnitName(PERSISTENT_UNIT);
		oDataJPACtx.setDefaultNaming(true);
		emf.getPersistenceUnitUtil();
		oDataJPACtx.getODataContext().setDebugMode(true);
		this.oDataJPAContext = oDataJPACtx;
		return oDataJPACtx;
	}

	@Override
	public ODataSingleProcessor createCustomODataProcessor(ODataJPAContext oDataJPAContext) {
		return new CustomODataJPAProcessor(this.oDataJPAContext);
	}

}