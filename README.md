# Spring Cloud with MSA

[![Build Status](https://app.travis-ci.com/safecornerscoffee/spring-cloud-with-msa.svg?branch=master)](https://app.travis-ci.com/safecornerscoffee/spring-cloud-with-msa)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=safecornerscoffee_spring-cloud-with-msa&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=safecornerscoffee_spring-cloud-with-msa)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=safecornerscoffee_spring-cloud-with-msa&metric=coverage)](https://sonarcloud.io/summary/new_code?id=safecornerscoffee_spring-cloud-with-msa)

[Spring Cloud로 개발하는 마이크로서비스 애플리케이션(MSA)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%81%B4%EB%9D%BC%EC%9A%B0%EB%93%9C-%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4/)

## References

### Spring Cloud Gateway
- [URL Rewriting With Spring Cloud Gateway](https://www.springcloud.io/post/2022-03/spring-cloud-gateway-url-rewriting/)
> The RewritePath filter takes two arguments: a regular expression and a replacement string. The filter’s implementation works by simply executing the replaceAll() method on the request’s URI, using the provided parameters as arguments.

> A caveat of the way that Spring handles configuration files is we can’t use the standard ${group} replacement expression, as Spring will think it is a property reference and try to replace its value. To avoid this, we need to add a backslash between the “$” and “{” characters that will be removed by the filter implementation before using it as the actual replacement expression.

> A few remarks about the steps this code went through: Firstly, it calls the addOriginalRequestUrl(), which comes from the ServerWebExchangeUtils class, to store the original URL under the exchange’s attribute GATEWAY_ORIGINAL_REQUEST_URL_ATTR . The value of this attribute is a list to which we’ll append the received URL before going any modification and used internally by the gateway as part of the X-Forwarded-For header’s handling.
- [Writing Custom Spring Cloud Gateway Filters](https://www.baeldung.com/spring-cloud-custom-gateway-filters)
- [RewritePath Filter Factory](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-rewritepath-gatewayfilter-factory)
- [CustomFilter 등록하기](https://wonit.tistory.com/500)

### Spring Security
- [Spring Security without the WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
- [Spring Security - Lambda DSL](https://spring.io/blog/2019/11/21/spring-security-lambda-dsl)
- [Spring Security is deprecated WebSecurityConfigurerAdapter configuration class](https://www.mo4tech.com/spring-security-is-deprecated-websecurityconfigureradapter-configuration-class.html)

### Spring Cloud Config and Bus
- [Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)
- [Spring Cloud Bus](https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/)

### Kafka
- [Bitnami Docker Kafka](https://hub.docker.com/r/bitnami/kafka/)
- [UI for Apache Kafka](https://github.com/provectus/kafka-ui)
- [My Python/Java/Spring/Go/Whatever Client Won’t Connect to My Apache Kafka Cluster in Docker/AWS/My Brother’s Laptop. Please Help!](https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/)
- [Kafka Listeners – Explained](https://www.confluent.io/blog/kafka-listeners-explained/)
- [Apache Kafka 101](https://developer.confluent.io/learn-kafka/apache-kafka/events/)
- [Introduction to Connectors, Sinks, and Sources](https://developer.confluent.io/learn-kafka/kafka-connect/intro/)
- [Deploying Connect On Containers](https://developer.confluent.io/learn-kafka/kafka-connect/docker/)

#### List of Topics
```shell
$ docker run -it --rm \
    --network spring-cloud-network \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    bitnami/kafka:latest kafka-topics.sh --list  --bootstrap-server kafka:9092
```

#### Create a Topic
```shell
$ docker run -it --rm \
    --network spring-cloud-network \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    bitnami/kafka:latest kafka-topics.sh --create --topic quickstart --partitions 1 --bootstrap-server kafka:9092
```

#### Describe a Topic
```shell
$ docker run -it --rm \
    --network spring-cloud-network \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    bitnami/kafka:latest kafka-topics.sh --describe --topic topic-name --bootstrap-server kafka:9092
```

#### Producer and Consumer

```shell
$ docker run -it --rm \
    --network spring-cloud-network \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    bitnami/kafka:latest kafka-console-producer.sh --broker-list kafka:9092 --topic quickstart
```

```shell
$ docker run -it --rm \
    --network spring-cloud-network \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 \
    bitnami/kafka:latest kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic quickstart --from-beginning
```

### Monitoring
- [Setting up Grafana with Docker Compose](https://www.theairtips.com/post/setting-up-grafana-with-docker-compose)
- [Configure a Grafana Docker image](https://grafana.com/docs/grafana/v7.5/administration/configure-docker/)
- [Provisioning Grafana](https://grafana.com/docs/grafana/latest/administration/provisioning/)
- [Monitoring Spring Boot projects with Prometheus](https://keepgrowing.in/tools/monitoring-spring-boot-projects-with-prometheus/)
- [How to set up Grafana with Docker and connect it to Prometheus](https://keepgrowing.in/tools/how-to-set-up-grafana-with-docker-and-connect-it-to-prometheus/)
- [Grafana provisioning – How to configure data sources and dashboards](https://keepgrowing.in/tools/grafana-provisioning-how-to-configure-data-sources-and-dashboards/)

### CI/CD
- [Add coverage in a multi-module Maven project](https://docs.sonarcloud.io/enriching/test-coverage/java-test-coverage/)