package org.soapService.Repository;

import org.soapService.Domain.ReportUser;

import java.sql.SQLException;
import java.util.List;

public class ReportUserRepository implements BaseRepository<ReportUser> {
    public int add(ReportUser data) throws SQLException {
        return 0;
    }

    public List<ReportUser> getAll() throws SQLException {
        return null;
    }

    public ReportUser getById(int id) throws SQLException {
        return null;
    }

    public void update(int id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
