package org.soapServiceV2.Repository;

import org.soapServiceV2.Domain.AccountVerificationRequest;

import java.sql.SQLException;
import java.util.List;

public class AccountVerificationRequestRepository implements BaseRepository<AccountVerificationRequest> {
    public boolean add(AccountVerificationRequest data) throws SQLException {
        return false;
    }

    public List<AccountVerificationRequest> getAll() throws SQLException {
        return null;
    }

    public AccountVerificationRequest getById(int id) throws SQLException {
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
