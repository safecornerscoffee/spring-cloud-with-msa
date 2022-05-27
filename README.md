# Spring Cloud with MSA

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
