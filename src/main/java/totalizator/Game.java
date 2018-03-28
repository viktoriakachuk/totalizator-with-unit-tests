package totalizator;


import exeption.MarginException;
import exeption.NameException;
import exeption.ProbException;

import java.util.LinkedList;

public class Game {

    private double margin;
    private LinkedList<Bet> bets = new LinkedList<Bet>();
    private LinkedList<Member> members = new LinkedList<Member>();
    private String winner;

    public Game(double margin) throws MarginException {

            if (margin < 1 & margin > 20) { // testfail
                throw new MarginException();
            }
            this.margin = margin;

    }

    public void addMember(Member participant) throws NameException {

            if (this.members.isEmpty()) {
                this.members.add(participant);
            } else {
                for (Member m : this.members) {
                    if (m.getName().equals(participant.getName())) {
                        throw new NameException();
                    }
                }
                this.members.add(participant);
            }

    }

    public void addBet(Bet bet) throws ProbException {
        double totalProb = 0;
        for (Member m : this.members) {
            if (bet.getTeamName().equals(m.getName())) {
                bet.setKfc(100 / (m.getProb() + this.margin));
            }
        }

            for (Member m : this.members) {
                totalProb = totalProb + m.getProb();
            }
            if (totalProb != 100) {
                throw new ProbException();
            }
        bet.getClient().setBill(bet.getClient().getBill() - bet.getSum());
        this.bets.add(bet);

    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getMargin() {
        return margin;
    }

    public void setWinner() {
        this.winner = "Olivia";
    }

    public void showResult() {
        for (Bet b: this.bets){
            if(b.getTeamName().equals(this.winner)){
                System.out.println(b.getClient().getName() + " wins " + b.getAward());
                 b.getClient().setBill(b.getSum() + b.getAward());
            }
        }
    }

    public LinkedList<Member> getMembers() {
        return members;
    }
}