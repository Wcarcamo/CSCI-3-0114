package exam;

public class HockeyPlayer extends Player {
    public HockeyPlayer (String name) {
        super(name);
    }

    @Override
    public String talk() {
        return "He shoots, he scores, eh!!!";
    }
}
