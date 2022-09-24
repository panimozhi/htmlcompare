package com.jpc.tools.pgc.util;

import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static <T> T fromJSON(final TypeReference<T> type, final InputStream inputStream) {
		T data = null;

		try {
			data = new ObjectMapper().readValue(inputStream, type);
		} catch (Exception e) {
			// Handle the problem
		}
		return data;
	}
}
