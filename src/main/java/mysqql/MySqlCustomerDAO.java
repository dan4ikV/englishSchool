package mysqql;

import beans.Customer;
import dao.AbstractDAO;
import dao.DAOOwnException;
import dao.DAOOwnException;
import mysqql.MySqlDAOFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;


public class MySqlCustomerDAO extends AbstractDAO<Customer, Integer> {
    public MySqlCustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void prepareStUpdate(PreparedStatement stm, Customer obj) throws DAOOwnException {

    }

    @Override
    public String getSelectQuery() {
        return "SELECT FROM customers WHERE id = ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO customers (firstName, secondName, birthDate, login, password, status, rights, phone, email, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE customers SET firstName = ?, secondName = ?, login = ?, password = ?, status = ?, rights = ?, phone = ?, email = ?, address = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM customers WHERE id = ?;";
    }

    @Override
    public void prepareStInsert(PreparedStatement stm, Customer obj) throws DAOOwnException {
        try {
            stm.setNull(1, Types.INTEGER);
            stm.setString(2, obj.getFirstName());
            stm.setString(3, obj.getSecondName());
            stm.setDate(4, Date.valueOf(obj.getBirthDate()));
            stm.setString(5, obj.getLogin());
            stm.setString(6, obj.getPassword());
            stm.setString(7, obj.getStatus().toString());
            stm.setString(7, obj.getRights().toString());
            stm.setString(8, obj.getPhoneNumber());
            stm.setString(9, obj.getEmail());
            stm.setString(10, obj.getAddress());
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }

    @Override
    public List<Customer> parsingResultSet(ResultSet rs) throws DAOOwnException {
        LinkedList<Customer> result = new LinkedList<Customer>();
        try {
            while (rs.next()) {
                ExtCustomer cus = new ExtCustomer();
                cus.setFirstName(rs.getString("firstName"));
                cus.setSecondName(rs.getString("secondName"));
                cus.setBirthDate(rs.getDate("birthDate").toLocalDate());
                cus.setLogin(rs.getString("login"));
                cus.setPassword(rs.getString("password"));
                cus.setStatus(rs.getString("status"));
                cus.setRights(rs.getString("rights"));
                cus.setPhoneNumber(rs.getString("phone"));
                cus.setEmail(rs.getString("email"));
                cus.setAddress(rs.getString("address"));
                result.add(cus);
            }

        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
        return result;
    }

    @Override
    public Customer create() throws DAOOwnException {
        Customer customer = new Customer();
        return extCreate(customer);
    }

    private class ExtCustomer extends Customer {
        public void setFirstName(String firstname) {
            super.setFirstName(firstname);
        }

        public void setSecondName(String secondname) {
            super.setSecondName(secondname);
        }

        public void setBirthDate(LocalDate birthDate) {
            super.setBirthDate(birthDate);
        }

        public void setLogin(String login) {
            super.setLogin(login);
        }

        public void setPassword(String password) {
            super.setPassword(password);
        }

        public void setStatus(String status) {
            super.setStatus(status);
        }

        public void setRights(String rights) {
            super.setRights(rights);
        }

        public void setPhoneNumber(String phoneNumber) {
            super.setPhoneNumber(phoneNumber);
        }

        public void setEmail(String email) {
            super.setEmail(email);
        }

        public void setAddress(String address) {
            super.setAddress(address);
        }
    }
}
