package dao;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {
    private Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract void prepareStInsert(PreparedStatement stm, T obj) throws DAOOwnException;
    public abstract void prepareStUpdate(PreparedStatement stm, T obj) throws DAOOwnException;

    public abstract List<T> parsingResultSet(ResultSet rs) throws DAOOwnException;

    @Override
    public T extCreate(T obj) throws DAOOwnException {
        T instance;
        String query = getCreateQuery();
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            prepareStInsert(prSt, obj);
            int count = prSt.executeUpdate();
            if (count != 1) {
                throw new DAOOwnException("При створенні об'єкту було змінено більше ніж один запис " + count);
            }
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }

        query = getSelectQuery() + " WHERE  id = last_insert_id";

        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            prepareStInsert(prSt, obj);
            ResultSet rs = prSt.executeQuery();
            List<T> list = parsingResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DAOOwnException("Помилка з отриманими даними");
            }
            instance = list.iterator().next();


        } catch (Exception e) {
            throw new DAOOwnException(e);
        }

        return instance;
    }

    @Override
    public T getByPK(PK id) throws DAOOwnException {
        T object;
        String query = getSelectQuery();
        query+="WHERE id = ?";
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            prSt.setInt(1,id);
            ResultSet rs = prSt.executeQuery();
            List<T> list = parsingResultSet(rs);
            object = list.iterator().next();
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }

        return object;
    }

    @Override
    public void update(T obj) throws DAOOwnException {
        String query = getUpdateQuery();
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            prepareStUpdate(prSt, obj);
            int count = prSt.executeUpdate();
            if (count != 1) {
                throw new DAOOwnException("Error on update, changed more than 1 field " + count);
            }
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }

    @Override
    public void delete(T obj) throws DAOOwnException {
        String query = getDeleteQuery();
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            prSt.setObject(1, obj.getId());
            int count = prSt.executeUpdate();
            if (count != 1) {
                throw new DAOOwnException("Error on delete, deleted more than 1 field " + count);
            }
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
    }

    @Override
    public List<T> getAll() throws DAOOwnException {
        List<T> someList;
        String query = getSelectQuery();
        try (PreparedStatement prSt = connection.prepareStatement(query)) {
            ResultSet rs = prSt.executeQuery();
            someList = parsingResultSet(rs);
        } catch (Exception e) {
            throw new DAOOwnException(e);
        }
        return someList;
    }
}

