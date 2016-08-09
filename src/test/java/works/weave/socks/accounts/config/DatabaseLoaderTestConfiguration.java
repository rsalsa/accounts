package works.weave.socks.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import works.weave.socks.accounts.util.DataGenerator;

@Configuration
public class DatabaseLoaderTestConfiguration {
    @Bean
    public DataGenerator dataGenerator() {
        return new DataGenerator();
    }
}
