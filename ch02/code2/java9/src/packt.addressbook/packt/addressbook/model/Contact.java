package packt.addressbook.model;
public class Contact implements Comparable {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // get/set �Լ��� ���ǻ� �����մϴ�.
    public String toString() {
        return this.lastName + " " + this.phoneNumber;
    }

    public int compareTo(Object other) {
        Contact otherContact = (Contact)other;
        return this.lastName.compareTo(otherContact.lastName);
    }
}