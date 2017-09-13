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

import com.github.ppadial.pihole.client.apiClient.ApiClient;
import java.net.URI;

/**
 * pihole clients manager class.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public class PiHoleClientManager {

  /**
   * Gets a pihole client.
   *
   * @param piholeUri pihole base url (http://your.domain)
   * @return a pihole client
   * @since 0.1.0
   */
  public PiHoleClient getClient(final URI piholeUri) {

    // Creates the pihole Client based on the configuration
    ApiClient apiClient = new ApiClient(piholeUri.toString());

    return new PiHoleClient(apiClient);
  }
}

