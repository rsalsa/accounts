package works.weave.socks.accounts.entities;

import org.junit.Test;
import works.weave.socks.accounts.util.DataGenerator;
import works.weave.socks.accounts.util.EqualsTester;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class UnitAddress {
    private DataGenerator dummyData = new DataGenerator();

    @Test
    public void testPrint() {
        String s = dummyData.randomAddress().toString();
        assertThat(s.length(), is(greaterThan(0)));
    }

    @Test
    public void testEquals() {
        EqualsTester<Address> equalsTester = EqualsTester.newInstance( new Address("ID", "N", "S", "C", "P", "UK") );
        equalsTester.assertImplementsEqualsAndHashCode();
        equalsTester.assertEqual( new Address("ID", "N", "S", "C", "P", "UK"), new Address("ID", "N", "S", "C", "P", "UK") );
        equalsTester.assertNotEqual( new Address("ID", "N", "S", "C", "P", "UK"), new Address("ID2", "N", "S", "C", "P", "UK") );
    }
}
