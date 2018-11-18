package Data;

public class ToBeTested {
    private String firstName;
    private String lastName;

    private String fullName;
    public ToBeTested(String fName, String lName){
        this.firstName = fName;
        this.lastName = lName;
        this.fullName= this.firstName+ " " + this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getFullName(){
        return this.fullName;
    }

}
