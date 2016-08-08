package works.weave.socks.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import works.weave.socks.accounts.config.DatabaseLoaderTestConfiguration;
import works.weave.socks.accounts.repositories.CustomerRepository;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = DatabaseLoaderTestConfiguration.class)
public class ITDatabaseLoader {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DatabaseLoader databaseLoader;


    @Test
    public void shouldInsertData() throws Exception {
        databaseLoader.run("");
        log.info(Arrays.toString(customerRepository.findAll().toArray()));
        assertThat(customerRepository.count(), is(greaterThan(0L)));
    }
}
