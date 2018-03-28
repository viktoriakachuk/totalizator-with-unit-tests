package totalizator;

import exeption.BillException;
import exeption.SumException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BetTest {
    private Client client;
    @Before
    public void setUp() throws BillException {
         client = new Client("Mary Sue", 50);
    }
    @Test
    public void createBetPositive() throws SumException {
        String teamname = "Dinamo";
        double sum = 50;
        Bet bet = new Bet(client, sum, teamname);
        Assert.assertEquals(bet.getClient(), client);
        Assert.assertEquals(bet.getSum(),sum,0);
        Assert.assertEquals(bet.getTeamName(), teamname);

    }
    @Test (expected = SumException.class)
    public void createBetNegative() throws SumException {
        String teamname = "Dinamo";
        double sum = 51;
        Bet bet = new Bet(client, sum, teamname);

    }
}
