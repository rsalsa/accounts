package works.weave.socks.accounts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import works.weave.socks.accounts.DataGenerator;
import works.weave.socks.accounts.DatabaseLoader;
import works.weave.socks.accounts.repositories.AddressRepository;
import works.weave.socks.accounts.repositories.CardRepository;
import works.weave.socks.accounts.repositories.CustomerRepository;

@Configuration
public class DatabaseLoaderTestConfiguration {
    @Bean
    @Autowired
    public DatabaseLoader databaseLoader(CustomerRepository customers, AddressRepository addresses, CardRepository cards, DataGenerator dataGenerator) {
        return new DatabaseLoader(customers, addresses, cards, dataGenerator);
    }

    @Bean
    public DataGenerator dataGenerator() {
        return new DataGenerator();
    }
}
