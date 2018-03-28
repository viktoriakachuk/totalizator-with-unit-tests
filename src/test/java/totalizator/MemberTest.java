package totalizator;

import org.junit.Assert;
import org.junit.Test;

public class MemberTest {
    @Test
    public void createMemberPositive(){
        String membername = "Dinamo";
        double memberprob = 40;
        Member member = new Member(membername, memberprob);
        Assert.assertEquals(member.getName(),membername);
        Assert.assertEquals(member.getProb(), memberprob, 0);
    }
}
