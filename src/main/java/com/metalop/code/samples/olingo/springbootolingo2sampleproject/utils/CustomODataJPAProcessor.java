package com.metalop.code.samples.olingo.springbootolingo2sampleproject.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.core.commons.ContentType;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPADefaultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.metalop.code.samples.olingo.springbootolingo2sampleproject.entity.Student;

public class CustomODataJPAProcessor extends ODataJPADefaultProcessor {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	public CustomODataJPAProcessor(ODataJPAContext oDataJPAContext) {
		super(oDataJPAContext);

	}

	@Override
	public ODataResponse createEntity(final PostUriInfo uriParserResultView, final InputStream content,
			final String requestContentType, final String contentType) throws ODataException {
		ODataResponse oDataResponse = null;
		oDataJPAContext.setODataContext(getContext());
		InputStream forwardedInputStream = content;
		try {
			if (uriParserResultView.getTargetEntitySet().getName().equals("Students")) {
				LOG.info("Students Entity Set Executed");

				if (requestContentType.equalsIgnoreCase(ContentType.APPLICATION_JSON.toContentTypeString())) {
					@SuppressWarnings("deprecation")
					JsonElement elem = new JsonParser().parse(new InputStreamReader(content));
					Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
					Student s = gson.fromJson(elem, Student.class);

					// Change some values
					s.setStudentID("Test" + s.getStudentID());
					forwardedInputStream = new ByteArrayInputStream(gson.toJson(s).getBytes());

				}
			}
			Object createdJpaEntity = jpaProcessor.process(uriParserResultView, forwardedInputStream,
					requestContentType);
			oDataResponse = responseBuilder.build(uriParserResultView, createdJpaEntity, contentType);
		} catch (JsonIOException | JsonSyntaxException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return oDataResponse;
	}
}
