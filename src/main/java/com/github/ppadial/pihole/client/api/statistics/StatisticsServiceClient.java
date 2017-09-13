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

package com.github.ppadial.pihole.client.api.statistics;

import com.github.ppadial.pihole.client.HttpStatusCode;
import com.github.ppadial.pihole.client.PiHoleException;
import com.github.ppadial.pihole.client.api.PiHoleServiceBase;
import com.github.ppadial.pihole.client.apiClient.ApiClient;
import com.github.ppadial.pihole.client.apiClient.ApiResponse;
import com.github.ppadial.pihole.client.model.PHStatistics;
import java.util.HashMap;
import java.util.Map;

/**
 * pihole Client for TRPlan API Endpoint.
 *
 * @author Paulino Padial
 * @see <a href="http://docs.gurock.com/pihole-api2/reference-users">reference-statistics</a>
 * @since 0.1.0
 */
public final class StatisticsServiceClient extends PiHoleServiceBase {

  /**
   * Creates a new instance of the user api client.
   *
   * @param apiClient the apiclient to use
   */
  public StatisticsServiceClient(final ApiClient apiClient) {
    super(apiClient);
  }

  /**
   * Gets all the current statistics.
   *
   * @return the statistics
   * @throws PiHoleException An error in the connection with pihole
   * @since 0.1.0
   */
  public final PHStatistics getStatistics() throws PiHoleException {
    final ApiResponse apiResponse;
    final PHStatistics phStatistics;

    // Do the query
    apiResponse = get("");

    Map<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException> choices =
        new HashMap<HttpStatusCode, com.github.ppadial.pihole.client.PiHoleException>() {{
          put(HttpStatusCode.INTERNAL_SERVER_ERROR, new PiHoleException("internal server error"));
        }};

    // Handle response
    phStatistics = handleApiResponse(apiResponse, PHStatistics.class, choices);

    // return the user if everything is Ok
    return phStatistics;
  }
}
