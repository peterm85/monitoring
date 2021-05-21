package monitoring.apifront.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@RibbonClient(name = "service", configuration = FakeRibbonConfiguration.class)
public class FakeFeignConfiguration {}
