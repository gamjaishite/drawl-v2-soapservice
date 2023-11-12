package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Domain.GetAllResponse;

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

    public GetAllResponse<CatalogRequest> getAll(int page, int pageSize) throws SQLException {
        return null;
    }

    public CatalogRequest getById(int id) throws SQLException {
        return null;
    }

    public int update(CatalogRequest id) throws SQLException {
        return 0;
    }

    public void deleteAll() throws SQLException {

    }

    public int delete(int id) throws SQLException {
        return 0;
    }
}
