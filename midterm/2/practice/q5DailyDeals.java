package practice;
public class q5DailyDeals {
    private q5Deal[] allDeals;

    public q5DailyDeals() {
        allDeals = new q5Deal[10];
    }

    public double findDealAverage() {
        double total = 0;
        for (q5Deal deal : allDeals) {
            total += deal.getTotal();
        }

        return total / allDeals.length;
    };
}
