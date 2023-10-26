package org.soapService.Repository;

import org.soapService.Domain.AccountVerificationRequest;

import java.sql.SQLException;
import java.util.List;

public class AccountVerificationRequestRepository implements BaseRepository<AccountVerificationRequest> {
    public int add(AccountVerificationRequest data) throws SQLException {
        return 0;
    }

    public List<AccountVerificationRequest> getAll() throws SQLException {
        return null;
    }

    public AccountVerificationRequest getById(int id) throws SQLException {
        return null;
    }

    public void update(int id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
