package org.soapServiceV2.Repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    public boolean add(T data) throws SQLException;

    public List<T> getAll() throws SQLException;

    public T getById(int id) throws SQLException;

    public boolean update(int id) throws SQLException;
    
    public boolean deleteAll() throws SQLException;

    public boolean delete(int id) throws SQLException;

}
