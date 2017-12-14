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
        return "SELECT id , login, password WHERE id = ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT into webUsers (login, password) VALUES(?, ?, ?, ?, ?);";

    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE webUser SET login = ?, password = ? WHERE id =?;";
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
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }

    @Override
    public void prepareStUpdate(PreparedStatement stm, WebUser obj) throws DAOOwnException {
        try {
            stm.setString(1, obj.getLogin());
            stm.setString(2, obj.getPassword());
            stm.setInt(3, obj.getId());
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
                switch (rs.getInt("rights_id")){
                    case 1:
                        wu.setRights("Admin");
                    case 2:
                        wu.setRights("Moderator");
                    case 3:
                        wu.setRights("Default");
                }
                switch (rs.getInt("status_id")){
                    case 1:
                        wu.setStatus("New");
                    case 2:
                        wu.setStatus("Active");
                    case 3:
                        wu.setStatus("Blocked");
                    case 4:
                        wu.setStatus("Banned");
                }
                result.add(wu);

            }

        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
        return result;
    }

    @Override
    public WebUser extCreate(WebUser obj) throws DAOOwnException {
        return super.extCreate(obj);
    }

    @Override
    public WebUser getByPK(Integer id) throws DAOOwnException {
        return super.getByPK(id);
    }

    @Override
    public void update(WebUser obj) throws DAOOwnException {
        super.update(obj);
    }

    @Override
    public void delete(WebUser obj) throws DAOOwnException {
        super.delete(obj);
    }

    @Override
    public List<WebUser> getAll() throws DAOOwnException {
        return super.getAll();
    }
    @Override
    public WebUser create() throws DAOOwnException {
        WebUser wu = new WebUser();
        return extCreate(wu);
    }
}
