package com.metalop.code.samples.olingo.springbootolingo2sampleproject.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = "com.metalop.code.samples.olingo.springbootolingo2sampleproject")
public class OlingoConfig {

	private static final Logger LOG = LoggerFactory.getLogger(OlingoConfig.class);

	@Autowired
	DataSource dataSource;

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean springEMF = new LocalContainerEntityManagerFactoryBean();
		EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
		springEMF.setJpaVendorAdapter(jpaVendorAdapter);
		springEMF.setDataSource(dataSource);
		Properties props = new Properties();
		props.setProperty("eclipselink.logging.level", "FINE");
		springEMF.setJpaProperties(props);
		springEMF.afterPropertiesSet();
		return springEMF.getObject();

	}

	/**
	 * Registers OData servlet bean with Spring Application context to handle
	 * ODataRequests.
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean odataServlet() {

		ServletRegistrationBean odataServRegstration = new ServletRegistrationBean(new CXFNonSpringJaxrsServlet(),
				"/odata.svc/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("javax.ws.rs.Application", "org.apache.olingo.odata2.core.rest.app.ODataApplication");
		initParameters.put("org.apache.olingo.odata2.service.factory",
				"com.metalop.code.samples.olingo.springbootolingo2sampleproject.utils.JPAServiceFactory");
		odataServRegstration.setInitParameters(initParameters);

		return odataServRegstration;

	}
}
