package com.github.ppadial.pihole.client.api.statistics;

import static org.assertj.core.api.Assertions.*;

import com.github.ppadial.pihole.client.PiHoleClient;
import com.github.ppadial.pihole.client.PiHoleClientManager;
import com.github.ppadial.pihole.client.model.PHStatistics;
import java.net.URI;
import org.testng.annotations.Test;

public class StatisticsServiceClientIT {

  @Test(enabled = false)
  public void testGetStatistics() throws Exception {
    PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
    PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI("http://pi.hole:81"));

    PHStatistics phStatistics = piHoleClient.statisticApi().getStatistics();

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
}