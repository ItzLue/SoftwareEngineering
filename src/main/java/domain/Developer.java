package domain;

import de.vandermeer.asciitable.AsciiTable;

public class Developer {
    AsciiTable at = new AsciiTable();
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
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        at.addRule();
        at.addRow("First name","Last name","ID");
        at.addRule();
        at.addRow(firstName,lastName,ID);
        at.addRule();
        return at.render();
    }
}
