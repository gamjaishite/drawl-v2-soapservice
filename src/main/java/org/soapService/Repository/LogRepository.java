package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.GetAllResponse;
import org.soapService.Domain.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogRepository implements BaseRepository<Log> {

    private static Connection conn = Database.getConnection();

    public int add(Log data) throws SQLException {
        String query = "INSERT INTO logs(req_desc, ip, endpoint_dest) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, data.getReqDesc());
        ps.setString(2, data.getIp());
        ps.setString(3, data.getEndpointDest());

        return ps.executeUpdate();
    }

    public GetAllResponse<Log> getAll(int page, int pageSize) throws SQLException {
        return null;
    }

    public Log getById(int id) throws SQLException {
        return null;
    }

    public int update(Log id) throws SQLException {
        return 0;
    }

    public void deleteAll() throws SQLException {

    }

    public int delete(int id) throws SQLException {
        return 0;
    }
}
