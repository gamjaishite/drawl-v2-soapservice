package org.soapService.Repository;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    public int add(T data) throws SQLException;

    public List<T> getAll() throws SQLException;

    public T getById(int id) throws SQLException;

    public void update(int id) throws SQLException;

    public void deleteAll() throws SQLException;

    public void delete(int id) throws SQLException;

}
