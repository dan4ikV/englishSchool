package java.mysqql;

import beans.Person;
import dao.GenericDAO;
import mysqql.MySqlDAOFactory;
import org.junit.Test;
import sun.java2d.loops.FillRect;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class MySqlPersonDAOTest {
    @Test
    public void create() throws Exception {
        Person person = new Person("danylo", "wasylyshyn", LocalDate.of(2002, 8, 1));
        MySqlDAOFactory factory = new MySqlDAOFactory();
        GenericDAO dao = factory.getDAO(factory.getConnection(), Person.class);
        dao.extCreate(person);
    }

}