package mysqql;

import beans.Person;
import beans.WebUser;
import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDAOFactory implements DAOFactory<Connection> {
    private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://servlab.mysql.tools:3306/servlab_danylo?useSSL=false";
    private static final String USERNAME = "servlab_danylo";
    private static final String PASSWORD = "224tljy5";
    private Map<Class, DAOCreator> daoCreatorMap;


    @Override
    public Connection getConnection() throws DAOOwnException {
        Connection connection = null;
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DAOOwnException(e);
        }
        return connection;
    }

    @Override
    public GenericDAO getDAO(Connection connection, Class daoClass) throws DAOOwnException {
        DAOCreator creator = daoCreatorMap.get(daoClass);

        if (creator == null) {
            throw new DAOOwnException("DAO object not found for class" + daoClass);
        }
        return creator.create(connection);

    }

    public MySqlDAOFactory() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        daoCreatorMap = new HashMap<Class, DAOCreator>();
        daoCreatorMap.put(Person.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlPersonDAO(connection);
            }
        });
        daoCreatorMap.put(WebUser.class, new DAOCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySqlPersonDAO(connection);
            }
        });



    }
}
