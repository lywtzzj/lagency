/*Yuwen Liu
yul905 11219371
cmpt270
 */
public class NumberGuesser {
    /**
     * make a decision if the number is valid
     * @param args: string
     */
    public static void main(String[] args){
        boolean a = true;
        do {
            System.out.println("Guess a number between 1 and 100: ");
            java.util.Scanner input = new java.util.Scanner(System.in);
            String userInput = input.nextLine();
            int guess = Integer.parseInt(userInput);
            if (guess < 1){
                System.out.println("Too low");
            }
            if (guess > 100){
                System.out.println("Too high");
            }
            if (guess < 100 && guess > 1) {
                a = false;
            }
        }while(a);
        System.out.println("That was a valid guess");
    }
}
