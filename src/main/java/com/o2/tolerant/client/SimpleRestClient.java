package com.o2.tolerant.client;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

@Service
public class SimpleRestClient {

	@Autowired
	private RestTemplate restTemplate;

	public String getMessage() {
		String result;
		try {
			String httpResult = restTemplate.getForObject("http://google.com", String.class);
			result = "Message SUCCESS result: " + httpResult;
		} catch (HttpStatusCodeException e) {
			result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
		} catch (RuntimeException e) {
			result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
		}
		return result;
	}

	public String getTolerantMatchScore() {
		String json = "...";
		String result = null;
		try {
			json = restTemplate.getForObject("http://google.com", String.class);

			ReadContext ctx = JsonPath.parse(json);
			// List<String> authorsOfBooksWithISBN =
			// ctx.read("$.store.book[?(@.isbn)].author");
			String authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");

			result = authorsOfBooksWithISBN != null ? "Message SUCCESS result: " : "No scores match";
		} catch (HttpStatusCodeException e) {
			result = "Get FAILED with HttpStatusCode: " + e.getStatusCode() + "|" + e.getStatusText();
		} catch (RuntimeException e) {
			result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
		}

		return result;
	}
}
