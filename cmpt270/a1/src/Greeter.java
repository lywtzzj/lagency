/*Yuwen Liu
yul905 11219371
cmpt270
 */
public class Greeter {
    /**
     * get input name to product a new string
     * @param greeting input string
     * @return a string with input name
     */
    public static String introductions(String greeting) {
        System.out.println(greeting);

        // input
        System.out.print("Please enter your name: ");
        java.util.Scanner input = new java.util.Scanner(System.in);
        String outName = input.nextLine();
        System.out.print("Hello, " + outName + "\n");
        return outName;
    }
    public static void main(String[] args) {
        String username = introductions("Welcome to my Java program!");
        System.out.println("got username " + username);
    }
}
