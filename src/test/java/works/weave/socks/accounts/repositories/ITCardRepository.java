package works.weave.socks.accounts.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import works.weave.socks.accounts.DataGenerator;
import works.weave.socks.accounts.config.DatabaseLoaderTestConfiguration;
import works.weave.socks.accounts.entities.Card;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = DatabaseLoaderTestConfiguration.class)
public class ITCardRepository {
    @Autowired
    private CardRepository cardRepository;

    private DataGenerator dataGenerator = new DataGenerator();

    @Before
    public void removeAllData() {
        cardRepository.deleteAll();
    }

    @Test
    public void testCardSave() {
        Card original = dataGenerator.randomCard();
        Card saved = cardRepository.save(original);
        assertEquals(1, cardRepository.count());
        assertTrue(cardRepository.exists(saved.getId()));
        assertEquals(original, saved);
    }
}
