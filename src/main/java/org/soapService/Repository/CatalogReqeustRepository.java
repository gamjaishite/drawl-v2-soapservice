package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Domain.GetAllResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CatalogReqeustRepository implements BaseRepository<CatalogRequest> {

    private static Connection conn = Database.getConnection();
    public int add(CatalogRequest data) throws SQLException {
        String query = "INSERT INTO catalog_requests(uuid, title, description, poster, trailer, category) VALUES (?, ?, ?, ?, ? ,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUID.randomUUID().toString());
        ps.setString(2, data.getTitle());
        ps.setString(3, data.getDescription());
        ps.setString(4, data.getPoster());
        ps.setString(5, data.getTrailer());
        ps.setString(6, data.getCategory());

        return ps.executeUpdate();
    }

    public GetAllResponse<CatalogRequest> getAll(int page, int pageSize) throws SQLException {
        page = Math.max(page, 0);
        pageSize = Math.max(pageSize, 1);
        int offset = pageSize * (page - 1);
        String query = "SELECT id, uuid, title, description, poster, trailer, category, created_at, updated_at FROM catalog_requests LIMIT ? OFFSET ?";
        String totalPageQuery = "SELECT COUNT(*) AS total_page FROM catalog_requests";
        PreparedStatement ps = conn.prepareStatement(query);
        PreparedStatement totalPagePs = conn.prepareStatement(totalPageQuery);
        ps.setInt(1, pageSize);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();
        ResultSet totalPageRs = totalPagePs.executeQuery();
        int totalPage = 0;
        while (totalPageRs.next()) {
            totalPage = totalPageRs.getInt(1) / pageSize + 1;
        }

        List<CatalogRequest> rows = new ArrayList<>();

        while (rs.next()) {
            System.out.println(rs.getString(1));

            CatalogRequest row = new CatalogRequest();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setTitle(rs.getString(3));
            row.setDescription(rs.getString(4));
            row.setPoster(rs.getString(5));
            row.setTrailer(rs.getString(6));
            row.setCategory(rs.getString(7));
            row.setCreatedAt(rs.getString(8));
            row.setUpdatedAt(rs.getString(9));
            rows.add(row);
        }

        GetAllResponse<CatalogRequest> response = new GetAllResponse<>();
        response.setPage(page);
        response.setTotalPage(totalPage);
        response.setData(rows);
        return response;


    }

    public CatalogRequest getById(int id) throws SQLException {
        String query = "SELECT id, uuid, title, description, poster, trailer, category, created_at, updated_at FROM catalog_requests WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        List<CatalogRequest> rows = new ArrayList<>();
        while (rs.next()) {
            CatalogRequest row = new CatalogRequest();
            row.setId(rs.getInt(1));
            row.setUuid(rs.getString(2));
            row.setTitle(rs.getString(3));
            row.setDescription(rs.getString(4));
            row.setPoster(rs.getString(5));
            row.setTrailer(rs.getString(6));
            row.setCategory(rs.getString(7));
            row.setCreatedAt(rs.getString(8));
            row.setUpdatedAt(rs.getString(9));
            rows.add(row);
        }

        if (rows.size() == 0) {
            return null;
        }

        return rows.get(0);
    }

    public int update(CatalogRequest data) throws SQLException {
        return 0;
    }

    public void deleteAll() throws SQLException {

    }

    public int delete(int id) throws SQLException {
        String query = "DELETE FROM catalog_requests WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        return ps.executeUpdate();
    }
}
