package exam;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListOfCharacters {
    private Character[] listOfChars; // List of characters

    // Constructor
    // Reads name and number of votes for all characters into
    // listOfChars
   public ListOfCharacters() { // Implementation not shown }

    // Precondition: listOfChars contains Character objects
    // Postcondition: The vote percent for each character has
    // been calculated and updated
    public void computePercentageOfVotes() {
        int totalVotes = 0;
        for (Character character : listOfChars) {
            totalVotes += character.getTheVotes();
        }
        for (Character character : listOfChars) {
            character.setVotePercentage(
                (Double.valueOf(character.getTheVotes()) / totalVotes) * 100
            );
        }
    };

    // Precondition: listOfChars contains complete information
    // about all characters, including their updated vote
    // percents; each vote percent is a real number between
    // 0.0 and 100.0
    // Postcondition: Returns a list of possible characters,
    // namely those characters who got at least 10 percent
    // of the vote
    public ArrayList<Character> getPossibleList() {
        ArrayList<Character> possibleList;
        for (Character character : possibleList) {
            if (character.getVotePercentage() > 10.0) {
                possibleList.add(character)
            }
        }
        return possibleList;
    };

    // Precondition: listOfChars contains complete information
    // about all characters, including their updated vote
    // percents
    // Postcondition: The names of possible candidates only
    // have been printed, one per line, followed by
    // the candidate’s vote percent
    public void printPossible() {
        DecimalFormat fmt = new DecimalFormat("0.##");
        ArrayList<Character> possible = getPossibleList();
        for (Character character : possible) {
            System.out.println(
                character.getTheName() + " " + fmt.format(character.getVotePercentage()) + "%"
            );
        }
    };
}
