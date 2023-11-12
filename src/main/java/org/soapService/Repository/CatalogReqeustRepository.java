package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.CatalogRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class CatalogReqeustRepository implements BaseRepository<CatalogRequest> {

    private static Connection conn = Database.getConnection();
    public int add(CatalogRequest data) throws SQLException {
        String query = "INSERT INTO catalog_requests(title, description, poster, trailer, category, uuid) VALUES (?, ?, ?, ?, ? ,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, data.getTitle());
        ps.setString(2, data.getDescription());
        ps.setString(3, data.getPoster());
        ps.setString(4, data.getTrailer());
        ps.setString(5, data.getCategory());
        ps.setString(6, UUID.randomUUID().toString());


        return ps.executeUpdate();
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
