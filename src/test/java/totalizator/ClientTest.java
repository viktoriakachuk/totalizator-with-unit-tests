package totalizator;
import exeption.BillException;
import org.junit.Test;
import org.junit.Assert;

public class ClientTest {

@Test
    public void createClientPositive() throws BillException {
    String clientname = "Peter Parker";
    double clientbill = 120.0;
    Client client = new Client(clientname, clientbill);
    Assert.assertEquals(client.getName(), clientname);
    Assert.assertEquals(client.getBill(), clientbill, 0);
    }

@Test (expected = BillException.class)
    public void createClientNegative() throws BillException {
    String clientname = "Peter Parker";
    double clientbill = -15;
    Client client1 = new Client(clientname, clientbill);
    }
}











