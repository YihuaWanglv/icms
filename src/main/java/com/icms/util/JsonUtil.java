package com.icms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static final Gson g = new GsonBuilder().serializeNulls().create();
	
	public static Gson getGsonInstance() {
		return g;
	}
}
