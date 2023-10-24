package org.soapServiceV2.Repository;

import org.soapServiceV2.Domain.CatalogRequest;

import java.sql.SQLException;
import java.util.List;

public class CatalogReqeustRepository implements BaseRepository<CatalogRequest> {
    public boolean add(CatalogRequest data) throws SQLException {
        return false;
    }

    public List<CatalogRequest> getAll() throws SQLException {
        return null;
    }

    public CatalogRequest getById(int id) throws SQLException {
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
