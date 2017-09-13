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

package com.github.ppadial.pihole.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Statistics representation in Pi Hole.
 *
 * @author Paulino Padial
 * @see <a href="https://github.com/pi-hole/pi-hole#api">statistics</a>
 * @since 0.1.0
 */
public class PHStatistics {

  @JsonProperty("domains_being_blocked")
  public String domainsBeingBlocked;
  @JsonProperty("dns_queries_today")
  public String dnsQueriesToday;
  @JsonProperty("ads_blocked_today")
  public String adsBlockedToday;
  @JsonProperty("ads_percentage_today")
  public String adsPercentageToday;
  @JsonProperty("unique_domains")
  public String uniqueDomains;
  @JsonProperty("queries_forwarded")
  public String queriesForwarded;
  @JsonProperty("queries_cached")
  public String queriesCached;
  @JsonProperty("unique_clients")
  public String uniqueClients;
}