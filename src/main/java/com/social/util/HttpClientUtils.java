package com.social.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * Create By Deron Meng 2016-11-08.
 */
public class HttpClientUtils {
	private static final Logger log = Logger.getLogger(HttpClientUtils.class);

	/**
	 * send HTTP_GET request
	 * 
	 * @see The method will automatically close the connection, release the resources
	 * @param requestURL
	 *            request address (including parameters)
	 * @param decodeCharset
	 *            decoding character set, parsing the response data, which is the default UTF-8 null decoding
	 * @return remote host response body
	 */
	public static String sendGetRequest(String reqURL, String decodeCharset) {
		long responseLength = 0; // response length
		String responseContent = null; // response content
		HttpClient httpClient = new DefaultHttpClient(); // create the default httpClient instance
		HttpGet httpGet = new HttpGet(reqURL); // create org.apache.http.client.methods.HttpGet
		try {
			HttpResponse response = httpClient.execute(httpGet); // execute GET request
			HttpEntity entity = response.getEntity(); // get response entity
			if (null != entity) {
				responseLength = entity.getContentLength();
				responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
				EntityUtils.consume(entity);
			}
			log.info("========================request addr: " + httpGet.getURI());
			log.info("========================response status: " + response.getStatusLine());
			log.info("========================response lendth: " + responseLength);
			log.info("========================response content: " + responseContent);
		} catch (ClientProtocolException e) {
			log.error("========================The exception is usually the result of a protocol error,For example, the introduction of the "
					+ "HttpGet object when the protocol is not (written in'htp''http') or server side to return the content does not meet the "
					+ "requirements of the HTTP protocol, etc.the stack information is as follows: ");
			log.error(e.getStackTrace());
		} catch (ParseException e) {
			log.error("========================The stack information is as follows:  " +e.getStackTrace());
		} catch (IOException e) {
			log.error("========================The exception is usually caused by the network causes, such as the HTTP server is not started, the stack information is as follows: ");
			log.error(e.getStackTrace());
		} finally {
			httpClient.getConnectionManager().shutdown(); // close connection,release resources
		}
		return responseContent;
	}
	
	/**
	 * send request
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String sendRequest(String url) throws Exception {
		Objects.requireNonNull(url);
		String response = "";
		InputStream inPutStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL urlGet = new URL(url);
			urlConnection = (HttpURLConnection) urlGet.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// connection timed out 30 second
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // read timeout 30 second
			log.info("Request URL: " + url);

			urlConnection.connect();
			inPutStream = urlConnection.getInputStream();

			int size = inPutStream.available();
			byte[] jsonBytes = new byte[size];
			inPutStream.read(jsonBytes);
			response = new String(jsonBytes, "UTF-8");
			log.info("========================response string: " + response);
		} finally {
			IOUtils.closeQuietly(inPutStream);
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		return response;
	}
}
