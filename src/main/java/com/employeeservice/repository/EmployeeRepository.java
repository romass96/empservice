package com.employeeservice.repository;

import com.employeeservice.utils.DBUtil;
import com.employeeservice.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public String getPasswordByLogin(String login) {
        String password = null;
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT password FROM employees WHERE login = ?");
                statement.setString(1, login);
                rs = statement.executeQuery();
                rs.next();
                password = rs.getString(1);
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
        return password;
    }

    public boolean save(Employee employee) {
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "INSERT INTO employees(firstname, lastname, login, password) VALUES (?, ?, ?, ?)");
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getLogin());
                statement.setString(4, employee.getPassword());
                return statement.executeUpdate() != 0;
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
        return false;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection dbConnection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT firstname, lastname, login, password FROM employees");
                rs = statement.executeQuery();
                while (rs.next()) {
                    employees.add(new Employee(
                            rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4)));
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
        return employees;
    }

    public Employee getEmployeeByLogin(String login) {
        Employee employee = null;
        Connection dbConnection = DBUtil.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        if (dbConnection != null) {
            try {
                statement = dbConnection.prepareStatement(
                        "SELECT firstname, lastname, login, password FROM employees WHERE login = ?");
                statement.setString(1, login);
                rs = statement.executeQuery();
                while (rs.next()) {
                    employee = new Employee(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (statement != null) {
                        rs.close();
                    }
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employee;
    }
}
