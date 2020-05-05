package domain;

public class Developer {
    protected String firstName;
    protected String lastName;
    protected String ID;


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getID() {
        if(ID == null) {
            return null;
        }
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Developer(String firstName, String lastName) {
        if(firstName.matches("^[a-zA-Z]*$") && lastName.matches("^[a-zA-Z]*$") && firstName.length() > 1 && lastName.length() > 1) {
            this.firstName = firstName;
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Developer names must be 2 letters or larger and can only contain alphabetic letters");
        }
    }
    @Override
    public String toString() {
        return "First name:'" + firstName + '\'' +
                ", Last name:'" + lastName + '\'' +
                ", ID:'" + ID + '\'';
    }
}