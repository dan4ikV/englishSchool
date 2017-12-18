package mysqql;

import beans.Person;
import beans.UserRights;
import beans.UserStatus;
import beans.WebUser;
import dao.DAOOwnException;
import dao.GenericDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class runner2 {
    public static void main(String[] args) throws DAOOwnException, ParseException {
        MySqlDAOFactory factory;
        GenericDAO PersonDao;
        GenericDAO UserDao;

        factory = new MySqlDAOFactory();
        UserDao = factory.getDAO(factory.getConnection(), WebUser.class);
        PersonDao = factory.getDAO(factory.getConnection(), Person.class);
        WebUser user = new WebUser("danylo", "wasylyshyn", LocalDate.of(2002, 8, 01),
                "dan4ik", "danylo2002", UserStatus.Active, UserRights.Admin);
        System.out.println("Step 1 : creating");
        PersonDao.extCreate(user);
        WebUser dbUser = (WebUser) UserDao.extCreate(user);
        System.out.println(dbUser.toString());
        System.out.println("Step 2 : read");
        System.out.println(UserDao.getByPK(dbUser.getId()).toString());
        System.out.println("Step 3 : update");
        dbUser.setLogin("dan");
        UserDao.update(dbUser);
    }
}
