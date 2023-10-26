package org.soapService.Repository;

import org.soapService.Config.Database;
import org.soapService.Domain.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

    public List<Log> getAll() throws SQLException {
        return null;
    }

    public Log getById(int id) throws SQLException {
        return null;
    }

    public void update(int id) throws SQLException {

    }

    public void deleteAll() throws SQLException {

    }

    public void delete(int id) throws SQLException {

    }
}
