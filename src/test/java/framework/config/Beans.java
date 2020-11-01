package framework.config;

import framework.entity.BrowserValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Beans {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreResourceNotFound(true);
        configurer.setIgnoreUnresolvablePlaceholders(true);

        List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource("test.properties"));
        resources.add(new ClassPathResource("browser.properties"));

        configurer.setLocations(resources.toArray(new Resource[]{}));

        return configurer;
    }

    @Bean
    @Lazy
    public static BrowserValues browserValues(@Value("${browser.url}") String url,
                                              @Value("${browser.timeout}") int timeout,
                                              @Value("${browser.browser}") String browser,
                                              @Value("${browser.remote}") boolean isRemote) {
    return new BrowserValues(url, timeout, browser,
            System.getProperty("cucumber.is_remote") != null ?Boolean.parseBoolean(System.getProperty("cucumber.is_remote")) : isRemote );
    }
}
