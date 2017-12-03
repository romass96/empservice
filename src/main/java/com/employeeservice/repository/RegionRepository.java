package com.employeeservice.repository;

import com.employeeservice.utils.DBUtil;
import com.employeeservice.model.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionRepository {

    public List<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT id, name FROM regions");
                rs = statement.executeQuery();
                while (rs.next()) {
                    regions.add(new Region(rs.getLong(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return regions;
    }
}
