package exam;

import java.util.ArrayList;

public class PlayersClub {
    // All elements are references to Player objects
    private ArrayList<Player> playerList;

    // For each Player in the club, its name, followed by
    // the result of a call to its talk() method has been
    // printed; one line per Player
    public void allTalk() {
        for (Player player : playerList) {
            System.out.println(
                player.getTheName() + ": " + player.talk() 
            );
        }
    };
}
