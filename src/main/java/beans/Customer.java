package beans;

import dao.Identified;

import java.time.LocalDate;

public class Customer extends WebUser implements Identified<Integer>{

    private String phoneNumber;
    private String email;
    private String address;

    public Customer(String firstName, String secondName, LocalDate birthDate, String login, String password, UserStatus status, UserRights rights, String phoneNumber, String email, String address) {
        super(firstName, secondName, birthDate, login, password, status, rights);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Customer() {
        this.phoneNumber = "+380999999999";
        this.email = "example@gmail.com";
        this.address = "Avenue 6 bld 14 flat 2a";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
