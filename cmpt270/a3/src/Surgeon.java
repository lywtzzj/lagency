public class Surgeon extends Doctor {

    /**
     * A constructor that takes in the surgeon’s name.
     * @param name Surgeon's name
     */
    public Surgeon(String name){
        super(name);
        if (name == null){
            System.out.println("Invalid name");
        }
    }

    /**
     *  A method that returns a string representation of all the information
     *  about the doctor in a form suitable for printing.
     * @return new String
     */
    public String toString(){
        return "Surgeon: " + super.toString();
    }

    /**
     * test case
     * @param args arguments
     */
    public static void main(String[] args){
        Surgeon surgeon = new Surgeon("junshi");
        if (!surgeon.getName().equals("junshi")){
            System.out.println("Constructor is fail.");
        }
        String expect = "Surgeon: \n" +
                "Name: junshi\n" +
                "\n" +
                "This doctor does not have any patient\n";
        if (!surgeon.toString().equals(expect)){
            System.out.println("toString is fail.");
        }
//        System.out.println(surgeon.toString());
//        System.out.println(expect);
    }

}
