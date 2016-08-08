package works.weave.socks.accounts.entities;

import org.junit.Test;
import works.weave.socks.accounts.DataGenerator;
import works.weave.socks.accounts.util.EqualsTester;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class UnitCustomer {
    private DataGenerator dummyData = new DataGenerator();

    @Test
    public void testPrint() {
        Customer customer = new Customer("User", "Name", "user",
                singletonList(dummyData.randomAddress()),
                singletonList(dummyData.randomCard()));
        String s = customer.toString();
        assertThat(s.length(), is(greaterThan(0)));
    }

    @Test
    public void testEquals() {
        Customer customer = new Customer("ID", "User", "Name", "user",
                singletonList(dummyData.randomAddress()),
                singletonList(dummyData.randomCard()));
        Customer customerShouldEqual = new Customer("ID", "User", "Name", "user",
                singletonList(dummyData.randomAddress()),
                singletonList(dummyData.randomCard()));
        Customer customerNotEqual1 = new Customer("ID2", "User", "Name", "user",
                singletonList(dummyData.randomAddress()),
                singletonList(dummyData.randomCard()));
        Customer customerNotEqual2 = new Customer("ID", "User", "Name", "useraa",
                singletonList(dummyData.randomAddress()),
                singletonList(dummyData.randomCard()));
        EqualsTester<Customer> equalsTester = EqualsTester.newInstance( customer );
        equalsTester.assertImplementsEqualsAndHashCode();
        equalsTester.assertEqual( customer, customerShouldEqual );
        equalsTester.assertNotEqual( customer, customerNotEqual1 );
        equalsTester.assertNotEqual( customer, customerNotEqual2 );
    }
}
