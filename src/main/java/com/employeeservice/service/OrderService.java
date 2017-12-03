package com.employeeservice.service;

import com.employeeservice.model.Employee;
import com.employeeservice.model.Order;
import com.employeeservice.model.Region;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.repository.OrderRepository;
import com.employeeservice.repository.RegionRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private RegionRepository regionRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.employeeRepository = new EmployeeRepository();
        this.regionRepository = new RegionRepository();
    }

    public List<Region> getAllRegions() {
        return regionRepository.getAllRegions();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public Map<String, Date> getMinAndMaxOrderDate() {
        return orderRepository.getMinAndMaxOrderDate();
    }

    public Map<String, Integer> getOrdersCountByDateRange(Date beginDate, Date endDate) {
        return orderRepository.getOrdersCountByDateRange(beginDate, endDate);
    }

    public Map<String, Map<String, Integer>> getAllSumPrices() {
        return orderRepository.getAllSumPrices();
    }
}
