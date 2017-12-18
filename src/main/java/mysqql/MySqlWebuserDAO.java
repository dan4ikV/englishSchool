package mysqql;

import beans.WebUser;
import dao.AbstractDAO;
import dao.DAOOwnException;

import javax.jws.WebParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import static beans.UserRights.Admin;
import static beans.UserRights.Default;
import static beans.UserRights.Moderator;

public class MySqlWebuserDAO extends AbstractDAO<WebUser , Integer>{
    public MySqlWebuserDAO(Connection connection) {
        super(connection);
    }

    class ExtWebUser extends WebUser{
        @Override
        protected void setId(int id) {
            super.setId(id);
        }
    }
    @Override
    public String getSelectQuery() {
        return "SELECT wu.id, wu.login, wu.password , " +
                "pr.firstName, pr.secondName, pr.birthDate, rg.userRights,  st.userStatus FROM webUsers as wu " +
                "JOIN persons as pr on wu.persons_id = pr.id " +
                "JOIN rights as rg on wu.rights_id = rg.id " +
                "JOIN status as st on wu.status_id = st.id ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT into webUsers (login, password, persons_id, right_id, status_id) " +
                "VALUES(?, ?, " +
                "(SELECT id FROM persons WHERE firstName = ? AND secondName = ?)," +
                " (SELECT id FROM rights WHERE userRights = ?)," +
                " (SELECT id FROM status WHERE userStatus = ?))";

    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webUser SET login = ?, password = ?, " +
                "rights_id = (SELECT id FROM rights WHERE userRights = ?) , " +
                "status_id = (SELECT id FROM status WHERE userStatus = ?) WHERE id =?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE from webUsers WHERE id = ?;";
    }

    @Override
    public void prepareStInsert(PreparedStatement stm, WebUser obj) throws DAOOwnException {
        try {
            stm.setString(1, obj.getLogin());
            stm.setString(2, obj.getPassword());
            stm.setString(3,obj.getFirstName());
            stm.setString(4,obj.getSecondName());
            stm.setString(5,obj.getRights().toString());
            stm.setString(6,obj.getStatus().toString());
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }

    @Override
    public void prepareStUpdate(PreparedStatement stm, WebUser obj) throws DAOOwnException {
        try {
            stm.setString(1, obj.getLogin());
            stm.setString(2, obj.getPassword());
            stm.setString(3, obj.getRights().toString());
            stm.setString(4, obj.getStatus().toString());
            stm.setInt(5, obj.getId());
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }



    @Override
    public List<WebUser> parsingResultSet(ResultSet rs) throws DAOOwnException {
        LinkedList<WebUser> result = new LinkedList<WebUser>();
        try {
            while (rs.next()) {
                ExtWebUser wu = new ExtWebUser();
                wu.setFirstName(rs.getString("firstName"));
                wu.setSecondName(rs.getString("secondName"));
                wu.setBirthDate(rs.getDate("birthDate").toLocalDate());
                wu.setLogin(rs.getString("login"));
                wu.setPassword(rs.getString("password"));
                wu.setRights(rs.getInt("rights_id"));
                wu.setStatus(rs.getInt("status_id"));
                result.add(wu);

            }

        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
        return result;
    }

    @Override
    public String getWhereForSelectQuery() {
        return "WHERE wu.id = ";
    }

    @Override
    public WebUser create() throws DAOOwnException {
        WebUser wu = new WebUser();
        return extCreate(wu);
    }
}
