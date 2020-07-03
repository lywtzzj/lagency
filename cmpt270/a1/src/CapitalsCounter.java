/*Yuwen Liu
yul905 11219371
cmpt270
 */
public class CapitalsCounter {
    /**
     * count how many cap
     * @param args a string may have cap or not
     * @return int number
     */
    private static int countCaps(String args){
        int count = 0;
        for (int i = 0; i < args.length(); i++){
            if(Character.isUpperCase(args.charAt(i))){
                count += 1;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        // a string test method
        if(countCaps("IHaveFiveCaptialLetters") == 5){
            System.out.println(":)");
        }
        else{
            System.out.println(":(");
        }
    }
}
