package exam;

public class Character {
    private String theName; // Name of character
    private int theNumberOfVotes; // Number of votes
    private double votePercentage; // Percentage of the vote

    // Constructor
    // votePercentage set to 0.0; actual value set later
    public Character(String nm, int nv) {
        theName = nm;
        theNumberOfVotes = nv;
        votePercentage = 0.0;
    }

    // Mutator to set votePercentage equal to vp
    // Precondition: vote percentage is a real number between
    // 0.0 and 100.0
    public void setVotePercentage(double vp) {
        votePercentage = vp;
    }

    // Accessors
    public String getTheName() {
        return theName;
    }

    public int getTheVotes() {
        return theNumberOfVotes;
    }

    public double getVotePercentage() {
        return votePercentage;
    }
}