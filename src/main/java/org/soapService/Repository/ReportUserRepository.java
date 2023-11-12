package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Domain.ReportUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ReportUserRepository implements BaseRepository<ReportUser> {
    private static Connection conn = Database.getConnection();

    public int add(ReportUser data) throws SQLException {
        String query = "INSERT INTO report_users(content, reporter_id, reported_id, uuid) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, data.getContent());
        ps.setString(2, data.getReporterId());
        ps.setString(3, data.getReportedId());
        ps.setString(4, UUID.randomUUID().toString());

        return ps.executeUpdate();
    }

    public GetAllResponse<ReportUser> getAll(int page, int pageSize) throws SQLException {
        return null;
    }

    public ReportUser getById(int id) throws SQLException {
        return null;
    }

    public void update(ReportUser id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
