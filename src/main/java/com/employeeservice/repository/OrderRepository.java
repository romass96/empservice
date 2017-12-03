package com.employeeservice.repository;

import com.employeeservice.utils.DBUtil;
import com.employeeservice.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class OrderRepository {

    public void save(Order order) {
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "INSERT INTO ORDERS(employee_login, region_id, price, order_date) VALUES (?, ?, ?, ?)");
                statement.setString(1, order.getEmployee().getLogin());
                statement.setLong(2, order.getRegion().getId());
                statement.setInt(3, order.getPrice());
                statement.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
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
    }

    public Map<String, Integer> getOrdersCountByDateRange(Date beginDate, Date endDate) {
        Map<String, Integer> data = new TreeMap<>();
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT order_date, COUNT(*) AS order_count FROM orders " +
                                "GROUP BY order_date HAVING order_date BETWEEN ? AND ?");
                statement.setDate(1, new java.sql.Date(beginDate.getTime()));
                statement.setDate(2, new java.sql.Date(endDate.getTime()));
                rs = statement.executeQuery();
                while (rs.next()) {
                    data.put(rs.getDate(1).toString(),rs.getInt(2));
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
        return data;
    }

    public Map<String, Date> getMinAndMaxOrderDate() {
        Map<String,Date> map = new HashMap<>();
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT MIN(order_date), MAX(order_date) FROM orders");
                rs = statement.executeQuery();
                while (rs.next()) {
                    map.put("min",new Date(rs.getDate(1).getTime()));
                    map.put("max",new Date(rs.getDate(2).getTime()));
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
        return map;
    }

    public Map<String, Map<String, Integer>> getAllSumPrices() {
        Map<String, Map<String, Integer>> data = new TreeMap<>();
        Map<String, Integer> values = null;
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT CONCAT(s1.employee_firstname, ' ', s1.employee_lastname) AS fullname," +
                                "        s1.country_name AS country, COALESCE(sum(s2.o_price), 0) AS order_sum FROM" +
                                "  (SELECT c.id AS country_id, c.name AS country_name, e.login AS employee_login," +
                                "     e.firstname AS employee_firstname, e.lastname AS employee_lastname" +
                                "   FROM countries c, employees e) s1" +
                                "  LEFT JOIN" +
                                "  (SELECT e.login AS e_login, c.id AS c_id, o.price AS o_price FROM orders o" +
                                "    INNER JOIN regions r ON o.region_id = r.id" +
                                "    INNER JOIN countries c ON r.country_id = c.id" +
                                "    INNER JOIN employees e ON o.employee_login = e.login) s2" +
                                "  ON s1.country_id = s2.c_id AND s1.employee_login = s2.e_login" +
                                "  GROUP BY fullname, country");
                rs = statement.executeQuery();
                String currentEmployee = "";
                while (rs.next()) {
                    String employee = rs.getString(1);
                    if (!currentEmployee.equals(employee)) {
                        if (values != null) {
                            data.put(currentEmployee,values);
                        }
                       currentEmployee = employee;
                       values = new HashMap<>();
                    }
                    values.put(rs.getString(2), rs.getInt(3));
                }
                data.put(currentEmployee,values);

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
        return data;
    }


}
