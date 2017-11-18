package beans;

import dao.Identified;

import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.Enumeration;

public class WebUser extends Person implements Identified<Integer>{
    private String login;
    private String password;
    private int id;
    private UserStatus status;
    private UserRights rights;


    public WebUser(String firstName, String secondName, int id, LocalDate birthDate, String login, String password, UserStatus status, UserRights rights) {
        super(firstName, secondName, birthDate);
        this.login = login;
        this.password = password;
        this.id = id;
        this.status = status;
        this.rights = rights;
    }

    public WebUser() {
        this.login = "login";
        this.password = "pass";
        this.status = UserStatus.New;
        this.rights = UserRights.Default;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRights getRights() {
        return rights;
    }

    public void setRights(UserRights rights) {
        this.rights = rights;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId() {

    }

    public void setStatus(String status) {
        switch (status) {
            case "New":
                this.status = UserStatus.New;
                break;
            case "Active":
                this.status = UserStatus.Active;
                break;
            case "Banned":
                this.status = UserStatus.Banned;
                break;
            case "Blocked":
                this.status = UserStatus.Blocked;
                break;
        }
    }

    public void setRights(String rights) {
        switch (rights) {
            case "Default":
                this.rights = UserRights.Default;
                break;
            case "Moderator":
                this.rights = UserRights.Moderator;
                break;
            case "Admin":
                this.rights = UserRights.Admin;
                break;
        }
    }
}
