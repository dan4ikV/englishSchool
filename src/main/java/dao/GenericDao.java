package dao;

import java.io.Serializable;
import java.util.List;
import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
    public T create() throws DAOOwnException;

    public T extCreate(T obj) throws DAOOwnException;

    public T getByPK(PK id) throws DAOOwnException;

    public void update(T obj) throws DAOOwnException;

    public void delete(T obj) throws DAOOwnException;

    public List<T> getAll() throws DAOOwnException;

}

