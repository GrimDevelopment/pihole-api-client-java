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

package com.github.ppadial.pihole.client.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.ppadial.pihole.client.HttpStatusCode;
import com.github.ppadial.pihole.client.apiClient.ApiCallException;
import com.github.ppadial.pihole.client.apiClient.ApiClient;
import com.github.ppadial.pihole.client.apiClient.ApiResponse;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all test rail client services implementations.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public abstract class PiHoleServiceBase {

  private static final Logger LOG = LoggerFactory.getLogger(PiHoleServiceBase.class);

  //underlying api client
  protected ApiClient apiClient;
  //(de)-serializes objects to/from json
  protected ObjectMapper objectMapper;

  /**
   * creates a new instance.
   *
   * @param apiClient apiclient to use
   * @since 0.1.0
   */
  public PiHoleServiceBase(ApiClient apiClient) {
    LOG.debug(":: Constructor:: called");
    this.apiClient = apiClient;
    LOG.debug("Initialising ObjectMapper configuration");
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    //TODO: should probably remove this
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  /**
   * .
   *
   * @param apiResponse .
   * @param choices .
   * @throws com.github.ppadial.pihole.client.PiHoleException An error in the connection with
   * pihole
   * @since 0.1.0
   */
  protected void handleApiResponse(final ApiResponse apiResponse,
      Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices)
      throws com.github.ppadial.pihole.client.PiHoleException {
    if (!apiResponse.getHttpStatusCode().is2xxSuccessful()) {
      handleApiResponseKoChoices(apiResponse, choices);
    }
  }

  /**
   * .
   *
   * @param apiResponse .
   * @param clazz .
   * @param choices .
   * @param <T> .
   * @return .
   * @throws com.github.ppadial.pihole.client.PiHoleException An error in the connection with
   * pihole
   * @since 0.1.0
   */
  protected <T> T handleApiResponse(ApiResponse apiResponse, Class<T> clazz,
      Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices)
      throws com.github.ppadial.pihole.client.PiHoleException {
    T type = null;

    // if Response is OK, parse and return type
    if (apiResponse.getHttpStatusCode().is2xxSuccessful()) {
      type = handleApiResponseOk(apiResponse, clazz);
    } else { // if Response is Not OK, then parse choices
      handleApiResponseKoChoices(apiResponse, choices);
    }

    return type;
  }

  /**
   * .
   *
   * @param apiResponse .
   * @param typeReference .
   * @param choices .
   * @param <T> .
   * @return .
   * @throws com.github.ppadial.pihole.client.PiHoleException An error in the connection with
   * pihole
   */
  protected <T> T handleApiResponse(ApiResponse apiResponse, TypeReference<T> typeReference,
      Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices)
      throws com.github.ppadial.pihole.client.PiHoleException {
    T type = null;

    // if Response is OK, parse and return type
    if (apiResponse.getHttpStatusCode().is2xxSuccessful()) {
      type = handleApiResponseOk(apiResponse, typeReference);
    } else { // if Response is Not OK, then parse choices
      handleApiResponseKoChoices(apiResponse, choices);
    }

    return type;
  }

  private <T> T handleApiResponseOk(final ApiResponse apiResponse, final Class<T> clazz)
      throws com.github.ppadial.pihole.client.PiHoleException {
    final T type;
    try {
      type = objectMapper.readValue(apiResponse.getBody(), clazz);
    } catch (IOException ioException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(ioException);
    }
    return type;
  }

  private <T> T handleApiResponseOk(final ApiResponse apiResponse,
      final TypeReference<T> typeReference)
      throws com.github.ppadial.pihole.client.PiHoleException {
    final T type;
    try {
      type = objectMapper.readValue(apiResponse.getBody(), typeReference);
    } catch (IOException ioException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(ioException);
    }
    return type;
  }

  private void handleApiResponseKoChoices(final ApiResponse apiResponse,
      final Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices)
      throws com.github.ppadial.pihole.client.PiHoleException {
    if (choices == null || choices.isEmpty()) {
      throw new com.github.ppadial.pihole.client.PiHoleException(
          "No Choices provided and response was not OK");
    }

    if (choices.containsKey(apiResponse.getHttpStatusCode())) {
      com.github.ppadial.pihole.client.PiHoleException piholeException = choices
          .get(apiResponse.getHttpStatusCode());
      if (piholeException != null) {
        throw (piholeException);
      } else {
        throw new com.github.ppadial.pihole.client.PiHoleException(
            "Choice provided but not Exception specified, so raising a generic exception");
      }
    } else {
      throw new com.github.ppadial.pihole.client.PiHoleException(
          "Response with code " + apiResponse.getHttpStatusCode().value());
    }
  }

  /**
   * Do a Post Operation on the pihole service.
   *
   * @param uriSuffix api uri subfix to send POST
   * @return an api response
   * @throws com.github.ppadial.pihole.client.PiHoleException An Error during operation
   * @since 0.1.0
   */
  protected ApiResponse post(String uriSuffix)
      throws com.github.ppadial.pihole.client.PiHoleException {
    final ApiResponse apiResponse;
    try {
      apiResponse = apiClient.doPost(uriSuffix, null);
    } catch (ApiCallException apiCallException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(apiCallException);
    }
    return apiResponse;
  }

  /**
   * Do a Post Operation on the pihole service.
   *
   * @param uriSuffix api uri subfix to send POST
   * @param data map with properties to send in the request body
   * @return an api response
   * @throws com.github.ppadial.pihole.client.PiHoleException An Error during operation
   * @since 0.1.0
   */
  protected ApiResponse post(String uriSuffix, Map<String, ? extends Object> data)
      throws com.github.ppadial.pihole.client.PiHoleException {
    final ApiResponse apiResponse;
    try {
      apiResponse = apiClient.doPost(uriSuffix, objectMapper.writeValueAsString(data));
    } catch (JsonProcessingException jsonProcessingException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(jsonProcessingException);
    } catch (ApiCallException apiCallException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(apiCallException);
    }
    return apiResponse;
  }

  /**
   * Do a GET Operation on the pihole service.
   *
   * @param uriSuffix api uri subfix to send GET
   * @return an api response
   * @throws com.github.ppadial.pihole.client.PiHoleException An Error during operation
   * @since 0.1.0
   */
  protected ApiResponse get(String uriSuffix)
      throws com.github.ppadial.pihole.client.PiHoleException {
    final ApiResponse apiResponse;
    try {
      apiResponse = apiClient.doGet(uriSuffix);
    } catch (ApiCallException apiCallException) {
      throw new com.github.ppadial.pihole.client.PiHoleException(apiCallException);
    }
    return apiResponse;
  }
}
