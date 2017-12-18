package beans;

import dao.Identified;

import javax.xml.bind.annotation.XmlType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Enumeration;

public class WebUser extends Person implements Identified<Integer>{
    private String login;
    private String password;
    private UserStatus status;
    private UserRights rights;


    public WebUser(String firstName, String secondName, LocalDate birthDate, String login, String password, UserStatus status, UserRights rights) {
        super(firstName, secondName, birthDate);
        this.login = login;
        this.password = password;
        this.status = status;
        this.rights = rights;
    }

    public WebUser() {
        this.login = "login";
        this.password = "pass";
        this.status = UserStatus.New;
        this.rights = UserRights.Default;
    }

    public WebUser(String firstName, String secondName, Date birthDate, String login, String password) {
        setFirstName(firstName);
        setSecondName(secondName);
        setBirthDate(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.login = login;
        this.password = password;
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

    public void setStatus(int status) {
        switch (status) {
            case 1:
                this.status = UserStatus.New;
                break;
            case 2:
                this.status = UserStatus.Active;
                break;
            case 3:
                this.status = UserStatus.Banned;
                break;
            case 4:
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
    public void setRights(int rights) {
        switch (rights) {
            case 1:
                this.rights = UserRights.Default;
                break;
            case 2:
                this.rights = UserRights.Moderator;
                break;
            case 3:
                this.rights = UserRights.Admin;
                break;
        }
    }
}
