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

import com.github.ppadial.pihole.client.HttpStatusCode;
import com.github.ppadial.pihole.client.PiHoleException;
import com.github.ppadial.pihole.client.apiClient.ApiClient;
import com.github.ppadial.pihole.client.apiClient.ApiResponse;
import com.github.ppadial.pihole.client.model.PHForwardDestinations;
import com.github.ppadial.pihole.client.model.PHOverTimeData;
import com.github.ppadial.pihole.client.model.PHOverTimeDataForwards;
import com.github.ppadial.pihole.client.model.PHOverTimeDataQueryTypes;
import com.github.ppadial.pihole.client.model.PHQueries;
import com.github.ppadial.pihole.client.model.PHQuerySources;
import com.github.ppadial.pihole.client.model.PHQueryTypes;
import com.github.ppadial.pihole.client.model.PHSummary;
import com.github.ppadial.pihole.client.model.PHTopItems;
import com.github.ppadial.pihole.client.model.PHTopClients;
import java.util.HashMap;
import java.util.Map;

/**
 * pihole Client for TRPlan API Endpoint.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public final class PiHoleApi extends PiHoleServiceBase {

  /**
   * Creates a new instance of the user api client.
   *
   * @param apiClient the apiclient to use
   */
  public PiHoleApi(final ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * Gets all the current statistics.
   *
   * @return the statistics
   * @throws PiHoleException An error in the connection with pihole
   * @since 0.1.0
   */
  public final PHSummary getSummary() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHSummary phStatistics;

    // Do the query
    apiResponse = get("?summary");

    Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices =
        new HashMap<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException>() {{
          put(HttpStatusCode.INTERNAL_SERVER_ERROR, new PiHoleException("internal server error"));
        }};

    // Handle response
    phStatistics = handleApiResponse(apiResponse, PHSummary.class, choices);

    // return the user if everything is Ok
    return phStatistics;
  }

  public final PHOverTimeDataForwards overTimeDataForwards() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHOverTimeDataForwards overTimeDataForwards;

    // Do the query
    apiResponse = get("overTimeDataForwards");

    // Handle response
    overTimeDataForwards = handleApiResponse(apiResponse, PHOverTimeDataForwards.class, null);

    // return the user if everything is Ok
    return overTimeDataForwards;
  }

  public final PHOverTimeDataQueryTypes overTimeDataQueryTypes() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHOverTimeDataQueryTypes overTimeDataQueryTypes;

    // Do the query
    apiResponse = get("overTimeDataQueryTypes");

    // Handle response
    overTimeDataQueryTypes = handleApiResponse(apiResponse, PHOverTimeDataQueryTypes.class, null);

    // return the user if everything is Ok
    return overTimeDataQueryTypes;
  }

  public final PHForwardDestinations getForwardDestinationNames() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHForwardDestinations phForwardDestinations;

    // Do the query
    apiResponse = get("getForwardDestinationNames");

    // Handle response
    phForwardDestinations = handleApiResponse(apiResponse, PHForwardDestinations.class, null);

    // return the user if everything is Ok
    return phForwardDestinations;
  }

  public final PHTopItems topItems() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHTopItems phTopItems;

    // Do the query
    apiResponse = get("topItems");

    // Handle response
    phTopItems = handleApiResponse(apiResponse, PHTopItems.class, null);

    // return the user if everything is Ok
    return phTopItems;
  }

  public final PHTopClients topClients() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHTopClients phTopSources;

    // Do the query
    apiResponse = get("topClients");

    // Handle response
    phTopSources = handleApiResponse(apiResponse, PHTopClients.class, null);

    // return the user if everything is Ok
    return phTopSources;
  }

  public final PHQuerySources getQuerySources() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQuerySources phQuerySources;

    // Do the query
    apiResponse = get("getQuerySources");

    // Handle response
    phQuerySources = handleApiResponse(apiResponse, PHQuerySources.class, null);

    // return the user if everything is Ok
    return phQuerySources;
  }

  public final PHQueryTypes getQueryTypes() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQueryTypes phQueryTypes;

    // Do the query
    apiResponse = get("getQueryTypes");

    // Handle response
    phQueryTypes = handleApiResponse(apiResponse, PHQueryTypes.class, null);

    // return the user if everything is Ok
    return phQueryTypes;
  }

  public final PHForwardDestinations getForwardDestinations() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHForwardDestinations phForwardDestinations;

    // Do the query
    apiResponse = get("getForwardDestinations");

    // Handle response
    phForwardDestinations = handleApiResponse(apiResponse, PHForwardDestinations.class, null);

    // return the user if everything is Ok
    return phForwardDestinations;
  }

  public final PHQueries getAllQueries() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQueries queries;

    // Do the query
    apiResponse = get("getAllQueries");

    // Handle response
    queries = handleApiResponse(apiResponse, PHQueries.class, null);

    // return the user if everything is Ok
    return queries;
  }

  public final PHQueries getAllQueriesByDomain(String domain) throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQueries queries;

    // Do the query
    apiResponse = get("getAllQueries&domain=" + domain);

    // Handle response
    queries = handleApiResponse(apiResponse, PHQueries.class, null);

    // return the user if everything is Ok
    return queries;
  }

  public final PHQueries getAllQueriesByClient(String client) throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQueries queries;

    // Do the query
    apiResponse = get("getAllQueries&client=" + client);

    // Handle response
    queries = handleApiResponse(apiResponse, PHQueries.class, null);

    // return the user if everything is Ok
    return queries;
  }

  public final PHQueries getAllQueriesBetweenTimeStamp(Long from, Long until) throws PiHoleException {
    final ApiResponse apiResponse;
    final PHQueries queries;

    // Do the query
    apiResponse = get("getAllQueries&from=" + String.valueOf(from) + "&until=" + String.valueOf(until));

    // Handle response
    queries = handleApiResponse(apiResponse, PHQueries.class, null);

    // return the user if everything is Ok
    return queries;
  }

  public final PHOverTimeData overTimeData10mins() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHOverTimeData phOverTimeData;

    // Do the query
    apiResponse = get("overTimeData10mins");

    // Handle response
    phOverTimeData = handleApiResponse(apiResponse, PHOverTimeData.class, null);

    // return the user if everything is Ok
    return phOverTimeData;
  }

  public final String recentBlocked() throws PiHoleException {
    final ApiResponse apiResponse;
    final String recentBlocked;

    // Do the query
    apiResponse = get("?recentBlocked");

    // Handle response
    recentBlocked = apiResponse.getBody();

    // return the user if everything is Ok
    return recentBlocked;
  }

  public final String version() throws PiHoleException {
    final ApiResponse apiResponse;
    final String version;

    // Do the query
    apiResponse = get("version");

    // Handle response
    version = apiResponse.getBody();

    // return the user if everything is Ok
    return version;
  }

  public final String type() throws PiHoleException {
    final ApiResponse apiResponse;
    final String type;

    // Do the query
    apiResponse = get("type");

    // Handle response
    type = apiResponse.getBody();

    // return the user if everything is Ok
    return type;
  }
}
