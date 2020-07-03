/**
 * @ author Yuwen Liu
 * @ vision 1.0
 * @ studentNumber: 11219371
 * @ NSID: yul905
 * @ course: CMPT270
 */
package command;

import container.WardAccess;
import java.util.LinkedList;

/**
 * class for display empty bed list
 */
public class EmptyBeds extends CommandStatus {
    private LinkedList<Integer> emptyBedList;

    /**
     * check if the bed is available
     */
    public void isEmptyBedList(){
        emptyBedList = WardAccess.ward().availableBeds();
        successful = true;
    }

    /**
     * take the empty bed list
     * @return emptyBedList
     */
    public LinkedList<Integer> getEmptyBedList(){
        if (wasSuccessful()){
            return emptyBedList;
        }
        throw new RuntimeException("empty bed list has error");
    }
}
