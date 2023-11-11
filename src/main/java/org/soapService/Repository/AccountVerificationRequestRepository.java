package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.AccountVerificationRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public List<AccountVerificationRequest> getAll() throws SQLException {
        String query = "SELECT id, uuid, user_id, status, created_at, updated_at FROM account_verification_requests";
        PreparedStatement ps = conn.prepareStatement(query);

        System.out.println(ps.executeQuery());
        return null;
    }

    public AccountVerificationRequest getById(int id) throws SQLException {
        String query = "SELECT id, uuid, user_id, status, created_at, updated_at FROM account_verification_requests WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        System.out.println(ps.executeQuery());
        return null;
    }

    public void update(int id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
