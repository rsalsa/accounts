package works.weave.socks.accounts.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import works.weave.socks.accounts.config.DatabaseLoaderTestConfiguration;
import works.weave.socks.accounts.entities.Customer;
import works.weave.socks.accounts.util.DataGenerator;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = DatabaseLoaderTestConfiguration.class)
public class ITCustomerRepository {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CardRepository cardRepository;

    private DataGenerator dataGenerator = new DataGenerator();

    @Before
    public void removeAllData() {
        customerRepository.deleteAll();
    }

    @Test
    public void testCustomerSave() {
        Customer original = new Customer("User", "Name", "user",
                singletonList(addressRepository.save(dataGenerator.randomAddress())),
                singletonList(cardRepository.save(dataGenerator.randomCard())));
        Customer saved = customerRepository.save(original);

        assertEquals(1, customerRepository.count());
        assertTrue(customerRepository.exists(saved.getId()));
        assertEquals(original, saved);
    }

    @Test
    public void testSearchCustomerByUserName() {
        Customer original = new Customer("User", "Name", "user",
                singletonList(addressRepository.save(dataGenerator.randomAddress())),
                singletonList(cardRepository.save(dataGenerator.randomCard())));
        customerRepository.save(original);

        List<Customer> found = customerRepository.findByUsername(original.getUsername());
        assertEquals(1, found.size());
        assertEquals(original, found.get(0));
    }
}
