/**
 * @ author: Yuwen Liu
 * @ vision: 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package userInterfaces;

import javax.swing.*;

public class DialogIO extends AbstractDialogIO {
    /**
     * read input string for dialog
     * @param prompt the string to be displayed as a prompt
     * @return
     */
    public String readString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * output outString string for dialog
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }

    /**
     * read a input int for dialog
     * @param prompt the string to be displayed as a prompt
     * @return
     */
    public int readInt(String prompt){
        String showString = JOptionPane.showInputDialog(null, prompt);
        int output = 0;
        if (showString != null && showString.length() >0){
            try {
                output = Integer.parseInt(showString);
            }catch (RuntimeException RE){
                outputString("The number entered" + output + "is not number. \n" +"Please input number in here");
                output = readInt(prompt);
            }
        }else {
            outputString("Please enter a value into the input field. \n");
            output = readInt(prompt);
        }
        return output;

    }
}
