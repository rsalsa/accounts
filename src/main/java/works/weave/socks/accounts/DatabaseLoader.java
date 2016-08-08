package works.weave.socks.accounts;

import com.mongodb.MongoTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import works.weave.socks.accounts.entities.Customer;
import works.weave.socks.accounts.repositories.AddressRepository;
import works.weave.socks.accounts.repositories.CardRepository;
import works.weave.socks.accounts.repositories.CustomerRepository;

import static java.util.Collections.singletonList;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private CustomerRepository customers;
    private AddressRepository addresses;
    private CardRepository cards;
    private DataGenerator dataGenerator;

    @Autowired
    public DatabaseLoader(CustomerRepository customers, AddressRepository addresses, CardRepository cards, DataGenerator dataGenerator) {
        this.customers = customers;
        this.addresses = addresses;
        this.cards = cards;
        this.dataGenerator = dataGenerator;
    }

    @Override
    public void run(String... strings) {
        try {
            Customer eve = new Customer("Eve", "Berger", "Eve_Berger",
                    singletonList(addresses.save(dataGenerator.randomAddress())),
                    singletonList(cards.save(dataGenerator.randomCard())));
            customers.save(eve);
            Customer user = new Customer("User", "Name", "user",
                    singletonList(addresses.save(dataGenerator.randomAddress())),
                    singletonList(cards.save(dataGenerator.randomCard())));
            customers.save(user);
            Customer user1 = new Customer("User1", "Name1", "user1",
                    singletonList(addresses.save(dataGenerator.randomAddress())),
                    singletonList(cards.save(dataGenerator.randomCard())));
            customers.save(user1);
        } catch (MongoTimeoutException e) {

        }
    }
}
