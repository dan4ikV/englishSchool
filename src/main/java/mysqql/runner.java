package mysqql;

import beans.Person;
import beans.WebUser;
import dao.DAOOwnException;
import dao.GenericDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.WeakHashMap;

public class runner {
    public static void main(String[] args) throws ParseException {
        Person person = new Person("danylo", "wasylyshyn", LocalDate.of(2002, 8, 1));
        MySqlDAOFactory factory = new MySqlDAOFactory();
        try {
            GenericDAO dao = factory.getDAO(factory.getConnection(), Person.class);
            dao.extCreate(person);
        } catch (DAOOwnException e) {
            e.printStackTrace();
        }

            WebUser user = new WebUser("danylo", "wasylyshyn", new SimpleDateFormat("dd/MM/yyyy").parse("01/08/2002"), "dan4ik", "danylo2002");

        try {
            GenericDAO dao = factory.getDAO(factory.getConnection(), WebUser.class);
            dao.extCreate(user);
        } catch (DAOOwnException e) {
            e.printStackTrace();
        }
    }
}
