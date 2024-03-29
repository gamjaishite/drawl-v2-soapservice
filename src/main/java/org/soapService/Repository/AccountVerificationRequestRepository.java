package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Domain.GetAllResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountVerificationRequestRepository implements BaseRepository<AccountVerificationRequest> {
    private static Connection conn = Database.getConnection();

    public int add(AccountVerificationRequest data) throws SQLException {
        String query = "INSERT INTO account_verification_requests(user_id, status, uuid) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, data.getUserId());
        ps.setString(2, "PENDING");
        ps.setString(3, UUID.randomUUID().toString());

        return ps.executeUpdate();
    }

    public GetAllResponse<AccountVerificationRequest> getAll(int page, int pageSize) throws SQLException {
        page = Math.max(page, 0);
        pageSize = Math.max(pageSize, 1);
        int offset = pageSize * (page - 1);
        String query = "SELECT id, uuid, user_id, status, created_at, updated_at FROM account_verification_requests LIMIT ? OFFSET ?";
        String totalPageQuery = "SELECT COUNT(*) AS total_page FROM account_verification_requests";
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

        List<AccountVerificationRequest> rows = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString(1));
            AccountVerificationRequest row = new AccountVerificationRequest();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setUserId(rs.getString(3));
            row.setStatus(rs.getString(4));
            row.setCreatedAt(rs.getString(5));
            row.setUpdatedAt(rs.getString(6));
            rows.add(row);
        }

        GetAllResponse<AccountVerificationRequest> response = new GetAllResponse<>();
        response.setPage(page);
        response.setTotalPage(totalPage);
        response.setData(rows);
        return response;
    }

    public GetAllResponse<AccountVerificationRequest> getAll(int page, int pageSize, String status)
            throws SQLException {
        page = Math.max(page, 0);
        pageSize = Math.max(pageSize, 1);
        int offset = pageSize * (page - 1);
        String query = "SELECT id, uuid, user_id, status, created_at, updated_at FROM account_verification_requests WHERE status = ? LIMIT ? OFFSET ?";
        String totalPageQuery = "SELECT COUNT(*) AS total_page FROM account_verification_requests WHERE status = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement totalPagePs = conn.prepareStatement(totalPageQuery);
        ps.setString(1, status);
        ps.setInt(2, pageSize);
        ps.setInt(3, offset);

        totalPagePs.setString(1, status);

        ResultSet rs = ps.executeQuery();
        ResultSet totalPageRs = totalPagePs.executeQuery();

        int totalPage = 0;
        while (totalPageRs.next()) {
            totalPage = (int) Math.ceil((double) totalPageRs.getInt(1) / pageSize);
            System.out.println("Total page " + totalPage);
            System.out.println("Total page rs " + totalPageRs.getInt(1));
        }

        List<AccountVerificationRequest> rows = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString(1));
            AccountVerificationRequest row = new AccountVerificationRequest();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setUserId(rs.getString(3));
            row.setStatus(rs.getString(4));
            row.setCreatedAt(rs.getString(5));
            row.setUpdatedAt(rs.getString(6));
            rows.add(row);
        }

        GetAllResponse<AccountVerificationRequest> response = new GetAllResponse<>();
        response.setPage(page);
        response.setTotalPage(totalPage);
        response.setData(rows);
        return response;
    }

    public AccountVerificationRequest getById(int id) throws SQLException {
        String query = "SELECT id, uuid, user_id, status, created_at, updated_at FROM account_verification_requests WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        List<AccountVerificationRequest> rows = new ArrayList<>();
        while (rs.next()) {
            AccountVerificationRequest row = new AccountVerificationRequest();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setUserId(rs.getString(3));
            row.setStatus(rs.getString(4));
            row.setCreatedAt(rs.getString(5));
            row.setUpdatedAt(rs.getString(6));
            rows.add(row);
        }

        if (rows.size() == 0) {
            return null;
        }

        return rows.get(0);
    }

    public int update(AccountVerificationRequest data) throws SQLException {
        String query = "UPDATE account_verification_requests SET status = ? WHERE user_id = ? AND status = 'PENDING'";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, data.getStatus());
        ps.setString(2, data.getUserId());
        return ps.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM account_verification_requests";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM account_verification_requests WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int delete(String userId) throws SQLException {
        String query = "DELETE FROM account_verification_requests WHERE user_id = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, userId);
        return ps.executeUpdate();
    }
}
