import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuwen Liu
 * @version 1.0
* yul905 11219371
* cmpt270
 */
public class PositiveEvenFinder {
    /**
     * get a number list then find and create a even list
     * @param args Integer List
     * @return Integer even List
     */
    private static List<Integer> PositiveEven (List<Integer> args){
        List<Integer> even =new ArrayList<Integer>();
        for(int i = 0; i < args.size() ; i++){
            int number = args.get(i);
            if (number > 0 && number % 2 == 0){
                even.add(number);
            }
        }
        return even;
    }
    public static void main(String[] args) {
        List <Integer> numberArray = new ArrayList<Integer>();
        numberArray.add(1);
        numberArray.add(2);
        numberArray.add(3);
        numberArray.add(4);
        numberArray.add(5);
        List<Integer> evenPositive = PositiveEven(numberArray);
        List <Integer> result = new ArrayList<Integer>();
        result.add(2);
        result.add(4);
        if (evenPositive.equals(result)) {
            System.out.println("the method gets correct list");
        }else {
            System.out.println("Test false the result should be " + evenPositive);
        }
    }
}
