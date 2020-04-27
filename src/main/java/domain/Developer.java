package domain;

public class Developer {

    protected String firstName;
    protected String lastName;
    protected String ID;
//test
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "First name: '" + firstName + '\'' +
                ", Last name: '" + lastName + '\'' +
                ", ID: '" + ID + '\'' + "\n";
    }
}
