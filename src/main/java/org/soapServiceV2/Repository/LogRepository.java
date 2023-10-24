package org.soapServiceV2.Repository;

import org.soapServiceV2.Domain.Log;

import java.sql.SQLException;
import java.util.List;

public class LogRepository implements BaseRepository<Log> {

    public boolean add(Log data) throws SQLException {
        return false;
    }

    public List<Log> getAll() throws SQLException {
        return null;
    }

    public Log getById(int id) throws SQLException {
        return null;
    }

    public boolean update(int id) throws SQLException {
        return false;
    }

    public boolean deleteAll() throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        return false;
    }
}
