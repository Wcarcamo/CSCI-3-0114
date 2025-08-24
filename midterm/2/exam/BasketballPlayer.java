package exam;

public class BasketballPlayer extends Player
{
   private String theTruth;

   // Constructor
   public BasketballPlayer(String nm) {
      super(nm);
   }

   // Method for the basketball player
   public String talk() {return theTruth;}
}
