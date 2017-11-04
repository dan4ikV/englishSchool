package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory<Context> {

    public interface DAOCreator<Context> {
        public GenericDao create(Context context);
    }

    public Context getConnection() throws DAOOwnException;

    public GenericDao getDAO(Context context, Class daoClass) throws DAOOwnException;

}
