package works.weave.socks.accounts.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import works.weave.socks.accounts.config.DatabaseLoaderTestConfiguration;
import works.weave.socks.accounts.entities.Address;
import works.weave.socks.accounts.util.DataGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = DatabaseLoaderTestConfiguration.class)
public class ITAddressRepository {
    @Autowired
    private AddressRepository addressRepository;

    private DataGenerator dataGenerator = new DataGenerator();

    @Before
    public void removeAllData() {
        addressRepository.deleteAll();
    }

    @Test
    public void testAddressSave() {
        Address original = dataGenerator.randomAddress();
        Address saved = addressRepository.save(original);
        assertEquals(1, addressRepository.count());
        assertTrue(addressRepository.exists(saved.getId()));
        assertEquals(original, saved);
    }

}
