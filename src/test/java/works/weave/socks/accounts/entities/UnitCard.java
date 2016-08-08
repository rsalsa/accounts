package works.weave.socks.accounts.entities;

import org.junit.Test;
import works.weave.socks.accounts.DataGenerator;
import works.weave.socks.accounts.util.EqualsTester;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class UnitCard {
    private DataGenerator dummyData = new DataGenerator();

    @Test
    public void testPrint() {
        String s = dummyData.randomCard().toString();
        assertThat(s.length(), is(greaterThan(0)));
    }

    @Test
    public void testEquals() {
        EqualsTester<Card> equalsTester = EqualsTester.newInstance( new Card("ID", "LongNum", "Expires", "ccv") );
        equalsTester.assertImplementsEqualsAndHashCode();
        equalsTester.assertEqual( new Card("ID", "LongNum", "Expires", "ccv"), new Card("ID", "LongNum", "Expires", "ccv") );
        equalsTester.assertNotEqual( new Card("ID", "LongNum", "Expires", "ccv"), new Card("ID2", "LongNum", "Expires", "ccv") );
    }
}
