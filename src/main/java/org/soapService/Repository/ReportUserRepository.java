package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Domain.ReportUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        page = Math.max(page, 0);
        pageSize = Math.max(pageSize, 1);
        int offset = pageSize * (page - 1);
        String query = "SELECT * FROM report_users ORDER BY created_at DESC LIMIT ? OFFSET ?";
        String totalPageQuery = "SELECT COUNT(*) AS total_page FROM report_users";
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement totalPagePs = conn.prepareStatement(totalPageQuery);
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();
        ResultSet totalPageRs = totalPagePs.executeQuery();
        int totalPage = 0;
        while (totalPageRs.next()) {
            totalPage = (int) Math.ceil((double) totalPageRs.getInt(1) / pageSize);
        }

        List<ReportUser> rows = new ArrayList<>();

        while (rs.next()) {
            ReportUser row = new ReportUser();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setContent(rs.getString(3));
            row.setReporterId(rs.getString(4));
            row.setReportedId(rs.getString(5));
            row.setCreatedAt(rs.getString(6));
            row.setUpdatedAt(rs.getString(7));

            rows.add(row);
        }

        GetAllResponse<ReportUser> response = new GetAllResponse<>();
        response.setPage(page);
        response.setTotalPage(totalPage);
        response.setData(rows);

        return response;
    }

    public ReportUser getById(int id) throws SQLException {
        return null;
    }

    public int update(ReportUser id) throws SQLException {
        return 0;
    }

    public void deleteAll() throws SQLException {

    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM report_users WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int deleteByReportedId(String reportedId) throws SQLException {
        String query = "DELETE FROM report_users WHERE reported_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, reportedId);
        return ps.executeUpdate();
    }

    public int deleteByUuid(String uuid) throws SQLException {
        String query = "DELETE FROM report_users WHERE uuid = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, uuid);
        return ps.executeUpdate();
    }
}
