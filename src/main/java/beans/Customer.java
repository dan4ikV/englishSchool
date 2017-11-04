package beans;

import java.time.LocalDate;

public class Customer extends WebUser{

    private String phoneNumber;
    private String email;
    private String address;

    public Customer(String firstName, String secondName, int id, LocalDate birthDate, String login, String password, UserStatus status, UserRights rights, String phoneNumber, String email, String address) {
        super(firstName, secondName, id, birthDate, login, password, status, rights);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Customer() {
        this.phoneNumber = "+380999999999";
        this.email = "example@gmail.com";
        this.address = "Avenue 6 bld 14 flat 2a";
    }

}
