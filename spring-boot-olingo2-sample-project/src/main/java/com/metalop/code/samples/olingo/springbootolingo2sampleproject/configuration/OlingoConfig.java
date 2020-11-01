package com.metalop.code.samples.olingo.springbootolingo2sampleproject.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

import io.pivotal.cfenv.core.CfCredentials;
import io.pivotal.cfenv.jdbc.CfJdbcEnv;

@Configuration
@Profile("cloud")
@ComponentScan(basePackages = "com.metalop.code.samples.olingo.springbootolingo2sampleproject")
public class OlingoConfig {

	private static final Logger LOG = LoggerFactory.getLogger(OlingoConfig.class);

	CfJdbcEnv cfJdbcEnv = new CfJdbcEnv();
	DataSourceProperties properties = new DataSourceProperties();
	CfCredentials hanaCredentials = cfJdbcEnv.findCredentialsByTag("hana");

	@Bean
	public DataSource dataSource() {

		String username = hanaCredentials.getUsername();
		String password = hanaCredentials.getPassword();

		String url = hanaCredentials.getUriInfo().toString();

		return DataSourceBuilder.create().type(HikariDataSource.class)
				.driverClassName(com.sap.db.jdbc.Driver.class.getName()).url(url).username(username).password(password)
				.build();

	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean springEMF = new LocalContainerEntityManagerFactoryBean();
		springEMF.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		springEMF.setDataSource(dataSource());
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
