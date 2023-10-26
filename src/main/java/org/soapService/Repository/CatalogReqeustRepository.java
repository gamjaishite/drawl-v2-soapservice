package org.soapService.Repository;

import org.soapService.Domain.CatalogRequest;

import java.sql.SQLException;
import java.util.List;

public class CatalogReqeustRepository implements BaseRepository<CatalogRequest> {
    public int add(CatalogRequest data) throws SQLException {
        return 0;
    }

    public List<CatalogRequest> getAll() throws SQLException {
        return null;
    }

    public CatalogRequest getById(int id) throws SQLException {
        return null;
    }

    public void update(int id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
