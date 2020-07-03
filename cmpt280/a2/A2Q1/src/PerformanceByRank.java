import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import lib280.list.LinkedList280;

public class PerformanceByRank {

    public static LinkedList280<Crew> readCrewData(String path) {
        Scanner infile = null;

        try {
            infile = new Scanner(new File(path));
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }

        // Initialize output list.
        LinkedList280<Crew> pirateCrew = new LinkedList280<Crew>();

        // While there is more stuff to read...
        while(infile.hasNext()) {
            // Read the three values for a Crew record
            int rank = infile.nextInt();
            double pay = infile.nextDouble();
            int sacks = infile.nextInt();

            // Create a crew object from the data
            Crew c = new Crew(rank, pay, sacks);

            // Place the new Crew instnace in the linked list.
            pirateCrew.insertFirst(c);
        }

        // Close the input file like a good citizen. :)
        infile.close();

        // Return the list of Crew objects.
        return pirateCrew;
    }


    public static void main( String args[] ) {
        // Read the data for Jack's pirate crew.

        // If you are getting a "File Not Found" error here, you may adjust the
        // path to piratecrew.txt as needed.
        LinkedList280<Crew> pirateCrew = readCrewData("A2Q1/src/piratecrew.txt");

		// TODO Write your solution here.

        // create a new list with 10 elements
        LinkedList280<Crew>[] piratesByRank = new LinkedList280[10];

        //  Initialize the elements of the array of linked lists piratesByRank.
        for (int i = 0; i < 10; i++){
            piratesByRank[i] = new LinkedList280<Crew>();
        }

        //
        pirateCrew.goFirst();
        while (pirateCrew.itemExists()){
            piratesByRank[pirateCrew.item().getRank()].insertFirst(pirateCrew.item());
            pirateCrew.goForth();
        }
        for (int i = 0; i < 10; i++){
            if (!piratesByRank[i].isEmpty()){
                double TPay = 0.0;
                int TSack = 0;
                piratesByRank[i].goFirst();
                while (piratesByRank[i].itemExists()) {
                    TPay += piratesByRank[i].item().getPay();
                    TSack += piratesByRank[i].item().getGrainSacks();
                    piratesByRank[i].goForth();
                }
                System.out.println("Jack’s rank " +i +" crew members were paid of " + TPay/TSack + " guineas per sack of grain plundered.");
            }
            else {
                System.out.println("empty list");
            }
        }


    }

}
