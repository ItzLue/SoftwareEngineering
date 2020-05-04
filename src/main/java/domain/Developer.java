package domain;

import de.vandermeer.asciitable.AsciiTable;

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
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Developer(String firstName, String lastName) {
        if(firstName.matches("^[a-zA-Z]*$") && lastName.matches("^[a-zA-Z]*$")) {
            this.firstName = firstName;
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Developer names cannot contain digits");
        }
    }

    @Override
    public String toString() {
        return "First name='" + firstName + '\'' +
                ", Last name='" + lastName + '\'' +
                ", ID='" + ID + '\'';
    }
}
