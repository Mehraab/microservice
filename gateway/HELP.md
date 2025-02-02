# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.1/maven-plugin/build-image.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.4.1/reference/web/reactive.html)
* [Reactive Gateway](https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway.html)
* [Consul Discovery](https://docs.spring.io/spring-cloud-consul/reference/discovery.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.1/reference/web/spring-security.html)
* [OAuth2 Client](https://docs.spring.io/spring-boot/3.4.1/reference/web/spring-security.html#web.security.oauth2.client)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/3.4.1/reference/web/spring-security.html#web.security.oauth2.server)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

