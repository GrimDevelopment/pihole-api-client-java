# Pi Hole API Client for java
> A pihole API client library for java

[![Maven Central](https://img.shields.io/maven-central/v/com.github.ppadial/pihole-api-client.svg?label=Maven%20Central)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.ppadial%22%20AND%20a%3A%22pihole-api-client%22)
[![Build Status](https://travis-ci.org/ppadial/pihole-api-client-java.svg?branch=master)]((https://travis-ci.org/ppadial/pihole-api-client-java.svg?branch=master)](https://travis-ci.org/ppadial/pihole-api-client-java))
[![Build status](https://ci.appveyor.com/api/projects/status/oho4oukq03qp6yt4/branch/master?svg=true)](https://ci.appveyor.com/project/ppadial/pihole-api-client-java/branch/master)
[![Coverage Status](https://coveralls.io/repos/github/ppadial/pihole-api-client-java/badge.svg?branch=master)](https://coveralls.io/github/ppadial/pihole-api-client-java?branch=master)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.github.ppadial%3Apihole-api-client)](https://sonarcloud.io/dashboard?id=com.github.ppadial%3Apihole-api-client)

This API currently support [PiHole](https://pi-hole.net/) 3.0 API version.

This project follows the [Maven parent POM](https://github.com/ppadial/parent-pom) archetype, see the instructions in 
All the information about:
* Configure dependencies
* Maven commands & targets available

## PiHole API Modules implementation status
| Module                      | Implemented | Require Auth  | PARAMS  |  
| :---                        | :---:       | :---:         | :---    |
| summary                     | YES         | NO            | NO      |
| summaryRaw                  | YES         | NO            | NO      |
| topItems                    | YES         | YES           | NO      |
| overTimeData10mins          | YES         | NO            | NO      |
| recentBlocked               | YES         | NO            | NO      |
| topClients                  | YES         | YES           | NO      |
| getQuerySources             | YES         | YES           | NO      |
| getForwardDestinations      | YES         | YES           | NO      |
| getQueryTypes               | YES         | YES           | NO      |
| getAllQueries               | YES         | YES           | optional params: from, until, domain, client |
| overTimeDataForwards        | YES         | YES           | NO      |
| getForwardDestinationNames  | YES         | YES           | NO      |
| overTimeDataQueryTypes      | YES         | YES           | NO      |

## Getting Started
Use this dependency in your maven config
```xml
<dependency>
  <groupId>com.github.ppadial</groupId>
  <artifactId>pihole-api-client</artifactId>
  <version>... last version ...</version>
</dependency>
```

## Use it!
```java
PiHoleClientManager piHoleClientManager = new PiHoleClientManager();
PiHoleClient piHoleClient = piHoleClientManager.getClient(new URI("http://pi.hole:81"), "AUTH TOKEN");

PHStatistics phStatistics = piHoleClient.api().summary();

// This object contains properties with the statistics info
// hStatistics.adsBlockedToday
// phStatistics.adsPercentageToday
// phStatistics.dnsQueriesToday
// phStatistics.domainsBeingBlocked
// phStatistics.queriesCached
// phStatistics.queriesForwarded
// phStatistics.uniqueDomains
```

If you want to see an example of each available method usage, please look at the [Integration Tests class](src/test/java/com/github/ppadial/pihole/client/api/PiHoleApiIT.java).

## Installation
Easy peasy
* Clone this repo: `git clone https://github.com/ppadial/pihole-api-client-java.git`
* Configure your maven system (settings.xml) as specified in [Parent POM](https://github.com/ppadial/parent-pom)

Wanna use IntelliJ ?
* Open the pom.xml with intelliJ
* Use the maven projects to build it using install target

Wanna use Command line?
* `mvn install`

## Contributing
see the [CONTRIBUTING file](CONTRIBUTING.md) in the source distribution

## Meta
Paulino Padial – [@paulinopadial](https://twitter.com/paulinopadial) – 

[https://github.com/ppadial/pihole-api-client-java](https://github.com/ppadial/)


## License
MIT License - see the [LICENSE file](LICENSE) in the source distribution


