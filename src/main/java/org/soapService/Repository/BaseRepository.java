package org.soapService.Repository;

import java.sql.SQLException;

import org.soapService.Domain.GetAllResponse;

public interface BaseRepository<T> {
    public int add(T data) throws SQLException;

    public GetAllResponse<T> getAll(int page, int pageSize) throws SQLException;

    public T getById(int id) throws SQLException;

    public int update(T data) throws SQLException;

    public void deleteAll() throws SQLException;

    public int delete(int id) throws SQLException;

}
