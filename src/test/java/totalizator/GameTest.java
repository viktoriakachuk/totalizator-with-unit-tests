package totalizator;

import exeption.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(Theories.class)
public class GameTest {
    private Member member = new Member("Dinamo", 40);
    private Client client = new Client("Peter Parker",120);
    private Bet bet = new Bet(client, 20, "Dinamo");
    private Game game;
    private List<Member> allmembers;
    @DataPoints("negative")
   // public static List<Double> marginNegative = new ArrayList<>(Arrays.asList(0.0,21.0,-17.0));
    public static double [] margNegative = {0, 21, -17};

    public GameTest() throws BillException, SumException {
    }

    @Test
    public void createGamePositive() throws MarginException {
        double margin = 18;
        game = new Game(margin);
        Assert.assertEquals(game.getMargin(), margin,0);

    }

    @Test (expected = MarginException.class)
    @Theory
    public void createGameNegative(@FromDataPoints("negative") double marg) throws MarginException {
        game = new Game (marg);
    }

    @Test
    public void testaddMemberPositive() throws MarginException, NameException {
        allmembers = new LinkedList<>();

        game = new Game(15);
        game.addMember(member);
        allmembers.add(member);

        Member member1 = new Member("Eagles", 30);
        game.addMember(member1);
        allmembers.add(member1);

        for (int i=0; i<allmembers.size(); i++){
            Assert.assertEquals(game.getMembers().get(i),allmembers.get(i));
        }
    }
    @Test (expected = NameException.class)
    public void testaddMemberNegative() throws MarginException, NameException {


        game = new Game(15);
        game.addMember(member);
        game.addMember(member);
    }

    @Test
    public void testaddBetPositive() throws MarginException, NameException, ProbException {
        game = new Game(10);
        game.addMember(member);

        Member member1 = new Member("Eagles", 60);
        game.addMember(member1);

        game.addBet(bet);

        double billsum =100;
        double coeff = 2;
        Assert.assertEquals(bet.getClient().getBill(), billsum,0);
        Assert.assertEquals(bet.getKf(),coeff,0);

    }

    @Test (expected = ProbException.class)
    public void testaddBetNegative() throws MarginException, NameException, ProbException {

        game = new Game(15);
        game.addMember(member);

        Member member1 = new Member("Eagles", 30);
        game.addMember(member1);

        game.addBet(bet);

    }

    @Test
    public void testShowResult() throws MarginException, NameException, ProbException {
        game = new Game(10);
        game.addMember(member);

        Member member1 = new Member("Eagles", 60);
        game.addMember(member1);

        game.addBet(bet);
        double award = 40;
        Assert.assertEquals(bet.getAward(), award,0);
    }
}
