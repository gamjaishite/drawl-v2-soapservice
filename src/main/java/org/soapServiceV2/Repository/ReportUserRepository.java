package org.soapServiceV2.Repository;

import org.soapServiceV2.Domain.ReportUser;

import java.sql.SQLException;
import java.util.List;

public class ReportUserRepository implements BaseRepository<ReportUser> {
    public boolean add(ReportUser data) throws SQLException {
        return false;
    }

    public List<ReportUser> getAll() throws SQLException {
        return null;
    }

    public ReportUser getById(int id) throws SQLException {
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
