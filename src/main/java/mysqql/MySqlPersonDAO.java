package mysqql;

import beans.Person;
import dao.AbstractDAO;
import dao.DAOOwnException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySqlPersonDAO extends AbstractDAO<Person, Integer> {
    public MySqlPersonDAO(Connection connection) {
        super(connection);
    }

    private class ExtPerson extends Person {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, firstName, secondName, birthDate from persons WHERE id =";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT into persons (firstName, secondName, birthDate) VALUES(?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE persons set firstName = ?, secondName = ?, birthDate = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE from persons WHERE id = ?;";
    }

    @Override
    public void prepareStInsert(PreparedStatement stm, Person obj) throws DAOOwnException {
        try {
            stm.setString(1, obj.getFirstName());
            stm.setString(2, obj.getSecondName());
            stm.setDate(3, Date.valueOf(obj.getBirthDate()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareStUpdate(PreparedStatement stm, Person obj) throws DAOOwnException {
        try {
            stm.setString(1, obj.getFirstName());
            stm.setString(2, obj.getSecondName());
            stm.setDate(3, Date.valueOf(obj.getBirthDate()));
            stm.setInt(4, obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> parsingResultSet(ResultSet rs) throws DAOOwnException {
        LinkedList<Person> result = new LinkedList<Person>();
        try {
            while (rs.next()) {
                ExtPerson per = new ExtPerson();
                per.setId(rs.getInt("id"));
                per.setFirstName(rs.getString("firstName"));
                per.setSecondName(rs.getString("secondName"));
                per.setBirthDate(rs.getDate("birthDate").toLocalDate());
                result.add(per);
            }

        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
        return result;
    }

    public Person create() throws DAOOwnException {
        Person person = new Person();
        return extCreate(person);
    }
}
