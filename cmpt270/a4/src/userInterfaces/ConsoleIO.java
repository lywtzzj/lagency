/**
 * @ author: Yuwen Liu
 * @ vision: 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package userInterfaces;

import java.util.Scanner;
//import

/**
 * do ConsoleIO like DialogIO with same name(almost) method
 */
public class ConsoleIO implements InputOutputInterface{

    private Scanner console = new Scanner(System.in);

    /**
     * display read String
     * @param prompt the String will be displayed
     * @return input String
     */

    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    /**
     * display input int
     * @param prompt the string to be displayed as a prompt
     * @return
     */
    public int readInt(String prompt){

        int output = 0;
        boolean successful = false;
        while (!successful){
            try {
                successful = true;
                System.out.println(prompt);
                output = console.nextInt();
            }catch (RuntimeException RE){
                successful = false;
                String EInput = console.nextLine();
                System.out.println("The number entered" + EInput + "is not number. \n" +"Please input number in here");
            }
        }
        console.nextLine();
        return output;
    }

    /**
     * make user can choose choices
     * @param options an array with the options that are presented to the user
     * @return
     */
    public int readChoice(String[] options){
        String prompt = "\nPlease select an option:";
        for (int i = 0; i < options.length; i++){
            prompt += "\n" + i + ": " + options[i];
        }
        prompt += "\nEnter the number of your selection: ";
        int output = readInt(prompt);
        if (output < 0 || output >= options.length) {
            outputString("You entered " + output + " is out of range"
                    + "\nPlease try again.  ");
            return readChoice(options);
        } else
            return output;
    }


    public void outputString(String outString) {
        System.out.print(outString);
    }
}
