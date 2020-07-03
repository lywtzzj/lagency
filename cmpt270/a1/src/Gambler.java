/*Yuwen Liu
yul905 11219371
cmpt270
 */
public class Gambler {
    /**
     * count average of random number
     * @param args input string
     */
    public static void main(String[] args) {
        double success = 0.0;
        double bets=0;
        for (int x = 0; x < 1000; x++){
            int stake = 100;
            bets=0;
            while (stake > 0 && stake < 200){
                double randNum = (Math.random());
                int play = (int)Math.round(randNum);
                bets +=1;
                if (play == 0){
                    stake -= 1;
                }else {
                    stake +=1;
                }
            }
            if (stake == 200){
                success += 1;
            }
            System.out.println("stake = " + stake+ " bets made = " + bets);
        }
        double aveSuccess = success / 1000;
        double aveBets = bets / 1000;
        System.out.println("average success = " + aveSuccess);
        System.out.println("average bets = " + aveBets);
    }
}
