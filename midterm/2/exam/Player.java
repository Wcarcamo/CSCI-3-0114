package exam;

public abstract class Player
{
   private String theName; // Unique pet name

   // Constructor
   public Player(String n) {
      theName = n;
   }

   // Returns the name for this player
   public String getTheName() {
      return theName;
   }

   // Abstract method for the player to talk
   public abstract String talk();
}
