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

package com.github.ppadial.pihole.client;

import com.github.ppadial.pihole.client.api.statistics.StatisticsServiceClient;
import com.github.ppadial.pihole.client.apiClient.ApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PiHole Client for endpoints described at <a href="https://github.com/pi-hole/pi-hole#api"> PiHole
 * Api</a>.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public class PiHoleClient {

  private static final Logger LOG = LoggerFactory.getLogger(PiHoleClient.class);

  //underlying Api apiClient
  private ApiClient apiClient;

  /**
   * Creates an instance of the apiClient and setups up required state.
   *
   * @param apiClient the api client to use
   */
  public PiHoleClient(ApiClient apiClient) {
    LOG.debug(":: Constructor:: called");
    this.apiClient = apiClient;
  }

  /**
   * Get access to the statistics Api functions.
   *
   * @return access to case functions catalog
   */
  public StatisticsServiceClient statisticApi() {
    return new StatisticsServiceClient(apiClient);
  }
}
