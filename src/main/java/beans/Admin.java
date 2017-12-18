package beans;

import sun.awt.EmbeddedFrame;

import java.time.LocalDate;

public class Admin extends WebUser {

    private AdminTypes type;

    public Admin(String firstName, String secondName, LocalDate birthDate, String login, String password, UserStatus status, UserRights rights, AdminTypes type) {
        super(firstName, secondName, birthDate, login, password, status, rights);
        this.type = type;
    }

    public Admin() {
        this.type = AdminTypes.Admin;
    }

    public AdminTypes getType() {
        return type;
    }

    public void setType(String types) {
        switch (types) {
            case "Admin":
                this.type = AdminTypes.Admin;
                break;
            case "SuperAdmin":
                this.type = AdminTypes.SuperAdmin;
                break;
            case "God":
                this.type = AdminTypes.God;
                break;
        }
    }


}
