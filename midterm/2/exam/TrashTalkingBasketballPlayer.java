package exam;

public class TrashTalkingBasketballPlayer extends BasketballPlayer {
   public TrashTalkingBasketballPlayer(String nm) {
      super(nm);
   }

    @Override
    public String talk() {
        return super.talk() + super.talk();
    }
}
