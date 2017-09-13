/*
 * Copyright (c) 2017. Paulino Padial
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.ppadial.pihole.client.apiClient;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client to talk to pihole API end points
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public class ApiClient {

  private static final Logger LOG = LoggerFactory.getLogger(ApiClient.class);
  private static Long MIN_MS_BETWEEN_RETRIES = Long.valueOf(250) /* 0,25s */;
  private static Long MAX_MS_BETWEEN_RETRIES = Long.valueOf(60000) /* 1m */;
  private static Integer MIN_RETRY_NUM = 1;
  private static Integer MAX_RETRY_NUM = 20;
  private static Long MIN_FLOOD_VALUE = Long.valueOf(1000); /* 1s */
  private static Long MAX_FLOOD_VALUE = Long.valueOf(60000); /* 1m */
  private HttpClient httpClient;
  private String url;
  private String username;
  private String password;
  private boolean retryOnFailureEnabled = false; /*disabled by default*/
  private Integer numOfRetries = MIN_RETRY_NUM;
  private Long millisBetweenRetries = MIN_MS_BETWEEN_RETRIES;
  private boolean antiFloodEnabled = false; /*disabled by default*/
  private Long antiFloodValue = MIN_FLOOD_VALUE;

  /**
   * Creates a new instance of the object.
   *
   * @param url url of the service
   * @since 0.1.0
   */
  public ApiClient(String url) {
    try {
      LOG.debug(":: Constructor method ::");
      this.url = url + "/admin/api.php";

      httpClient = HttpClientBuilder.create().build();
      LOG.debug("Created API client for {}", url);
    } catch (Exception e) {
      LOG.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  /**
   * Do an HTTP Get call against the pihole instance.
   *
   * @param uriSuffix suffix url to query
   * @return response object
   * @throws ApiCallException An error during the call to the service
   * @since 0.1.0
   */
  public ApiResponse doGet(String uriSuffix) throws ApiCallException {
    LOG.debug("Invoking {}", uriSuffix);
    final HttpGet httpGet = new HttpGet(url + uriSuffix);
    return doRequest(httpGet);
  }

  /**
   * Do an HTTP Post call against the pihole instance.
   *
   * @param uriSuffix suffix url to query
   * @param jsonData json request body data
   * @return response object
   * @throws ApiCallException An error during the call to the service
   * @since 0.1.0
   */
  public ApiResponse doPost(String uriSuffix, String jsonData) throws ApiCallException {
    LOG.debug("Invoking {} with jsonData {}", uriSuffix, jsonData);
    final HttpPost httpPost = new HttpPost(url + uriSuffix);
    // If we have json body data to send
    if (!Strings.isNullOrEmpty(jsonData)) {
      final StringEntity reqEntity;
      try {

        reqEntity = new StringEntity(jsonData);
        reqEntity.setContentType("application/json");
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new ApiCallException(unsupportedEncodingException);
      }
      httpPost.setEntity(reqEntity);
    }
    return doRequest(httpPost);
  }

  /**
   * Do an HTTP Post call against the pihole instance.
   *
   * @param uriSuffix suffix url to query
   * @return response object
   * @throws ApiCallException An error during the call to the service
   * @since 0.1.0
   */
  public ApiResponse doPost(String uriSuffix) throws ApiCallException {
    LOG.debug("Invoking {} without body", uriSuffix);
    final HttpPost httpPost = new HttpPost(url + uriSuffix);
    return doRequest(httpPost);
  }

  private ApiResponse doRequest(HttpUriRequest httpUriRequest) throws ApiCallException {
    LOG.debug("method called with AntiFlood={} RetryOnFailure={}", antiFloodEnabled,
        retryOnFailureEnabled);
    ApiResponse apiResponse;
    HttpResponse httpResponse;
    int numOfExecutions = 0;

    try {
      //do {
      httpResponse = httpClient.execute(httpUriRequest);
      apiResponse = ApiResponseMapper.from(httpResponse);
    } catch (Exception exception) {
      throw new ApiCallException(exception.getCause());
    }

    return apiResponse;
  }
}