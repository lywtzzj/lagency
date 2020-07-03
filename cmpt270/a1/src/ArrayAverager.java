/*Yuwen Liu
yul905 11219371
cmpt270
 */
public class ArrayAverager {
    /**
     *count the average of given number
     * @param array number list
     * @return double average number
     */
    public static double average (double[] array){
        double count = 0;
        for (int i = 0; i < array.length; i++){
            count += array[i];
        }
        double result = count / array.length;
        return result;
    }

    public static void main(String[] args) {
        double numberArray[] = {1, 3, 4, 5};
        double avgValue = average(numberArray);
        System.out.println("average = " + avgValue);
    }
}
