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

import static org.assertj.core.api.Assertions.*;

import com.github.ppadial.pihole.client.PiHoleClient;
import com.github.ppadial.pihole.client.PiHoleClientManager;
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
import java.net.URI;
import org.apache.http.auth.AUTH;
import org.testng.annotations.Test;

public class PiHoleApiIT {

  private final String AUTH_TOKEN =
      "YOUR_TOKEN";
  private final String SERVER = "http://pi.hole:81";

  @Test(enabled = false)
  public void getSummary() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI(SERVER));

    PHSummary phStatistics = piHoleClient.api().getSummary();

    assertThat(phStatistics).isNotNull();
    assertThat(phStatistics.adsBlockedToday).isNotBlank().isNotEmpty();
    assertThat(phStatistics.adsPercentageToday).isNotBlank().isNotEmpty();
    assertThat(phStatistics.dnsQueriesToday).isNotBlank().isNotEmpty();
    assertThat(phStatistics.domainsBeingBlocked).isNotBlank().isNotEmpty();
    assertThat(phStatistics.queriesCached).isNotBlank().isNotEmpty();
    assertThat(phStatistics.queriesForwarded).isNotBlank().isNotEmpty();
    assertThat(phStatistics.uniqueDomains).isNotBlank().isNotEmpty();
    assertThat(phStatistics.uniqueClients).isNotBlank().isNotEmpty();

  }

  @Test(enabled = false)
  public void type() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI(SERVER));

    String type = piHoleClient.api().type();

    assertThat(type).isNotEmpty().isNotBlank().isNotJavaBlank();
  }

  @Test(enabled = false)
  public void version() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI(SERVER));

    String version = piHoleClient.api().version();

    assertThat(version).isNotEmpty().isNotBlank().isNotJavaBlank();
  }

  @Test(enabled = false)
  public void getQueryTypes() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQueryTypes queryTypes = piHoleClient.api().getQueryTypes();

    assertThat(queryTypes).isNotNull();
  }

  @Test(enabled = false)
  public void overTimeData10mins() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI(SERVER));

    PHOverTimeData phOverTimeData = piHoleClient.api().overTimeData10mins();

    assertThat(phOverTimeData).isNotNull();
  }

  @Test(enabled = false)
  public void recentBlocked() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER));

    String recentBlocked = piHoleClient.api().recentBlocked();

    assertThat(recentBlocked).isNotNull().isNotEmpty();
  }

  @Test(enabled = false)
  public void topItems() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHTopItems phTopItems = piHoleClient.api().topItems();

    assertThat(phTopItems).isNotNull();
  }

  @Test(enabled = false)
  public void topClients() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHTopClients phTopclients = piHoleClient.api().topClients();

    assertThat(phTopclients).isNotNull();
  }

  @Test(enabled = false)
  public void getQuerySources() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQuerySources querySources = piHoleClient.api().getQuerySources();

    assertThat(querySources).isNotNull();
  }

  @Test(enabled = false)
  public void getForwardDestinations() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHForwardDestinations forwardDestinations = piHoleClient.api().getForwardDestinations();

    assertThat(forwardDestinations).isNotNull();
  }

  @Test(enabled = false)
  public void overTimeDataForwards() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHOverTimeDataForwards overTimeDataForwards = piHoleClient.api().overTimeDataForwards();

    assertThat(overTimeDataForwards).isNotNull();
  }

  @Test(enabled = false)
  public void getForwardDestinationNames() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHForwardDestinations forwardDestinations = piHoleClient.api().getForwardDestinationNames();

    assertThat(forwardDestinations).isNotNull();
  }

  @Test(enabled = false)
  public void overTimeDataQueryTypes() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHOverTimeDataQueryTypes overTimeDataQueryTypes = piHoleClient.api().overTimeDataQueryTypes();

    assertThat(overTimeDataQueryTypes).isNotNull();
  }

  @Test(enabled = false)
  public void getAllQueries() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQueries queries = piHoleClient.api().getAllQueries();

    assertThat(queries).isNotNull();
  }

  @Test(enabled = false)
  public void getAllQueriesByDomain() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQueries queries = piHoleClient.api().getAllQueriesByDomain("s.youtube.com");

    assertThat(queries).isNotNull();
  }

  @Test(enabled = false)
  public void getAllQueriesByClient() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQueries queries = piHoleClient.api().getAllQueriesByClient("192.168.1.102");

    assertThat(queries).isNotNull();
  }

  @Test(enabled = false)
  public void getAllQueriesBetweenTimeStamp() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager
        .getClient(new URI(SERVER), AUTH_TOKEN);

    PHQueries queries = piHoleClient.api().getAllQueriesBetweenTimeStamp(1505402266L, 1505403422L);

    assertThat(queries).isNotNull();
  }
}
