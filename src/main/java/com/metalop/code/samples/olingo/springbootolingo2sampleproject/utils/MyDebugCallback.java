package com.metalop.code.samples.olingo.springbootolingo2sampleproject.utils;

import org.apache.olingo.odata2.api.ODataDebugCallback;

public class MyDebugCallback implements ODataDebugCallback {
	@Override
	public boolean isDebugEnabled() {
		boolean isDebug = true; // true|configuration|user role check
		return isDebug;
	}
}
