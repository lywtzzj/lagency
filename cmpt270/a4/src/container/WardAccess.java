/**
 * @author Yuwen Liu
 * @vision 1.0
 * @studentNumber: 11219371
 * @NSID: yul905
 * @course: CMPT270
 */
package container;

import command.CommandStatus;
import entities.Ward;

public class WardAccess {
    // ward dictionary
    private static Ward ward;
    //empty constructor
    private WardAccess(){}

    /**
     * singleton pattern class implemented for Ward
     * @param name the name of ward
     * @param minBedLabel first bed label
     * @param maxBedLabel last bed label
     */
    public static void initialize(String name, int minBedLabel, int maxBedLabel){
        if (name == null|| name.equals("")){
            throw new RuntimeException("Please enter a really name");
        }
        if (minBedLabel < 0 || maxBedLabel < minBedLabel){
            throw new RuntimeException("The bed labels are not available");
        }
        if (ward != null) {
            throw new RuntimeException("The ward should be empty, you are not");
        }
        ward = new Ward(name, minBedLabel, maxBedLabel);
    }
    public static Ward ward(){
        if (ward == null)
            throw new RuntimeException("The ward should be empty");
        return ward;
    }

}
