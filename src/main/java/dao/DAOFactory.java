package dao;

public interface DAOFactory<Context> {

    public interface DAOCreator<Context> {
        public GenericDAO create(Context context);
    }

    public Context getConnection() throws DAOOwnException;

    public GenericDAO getDAO(Context context, Class daoClass) throws DAOOwnException;

}
