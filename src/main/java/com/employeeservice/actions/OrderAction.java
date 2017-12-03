package com.employeeservice.actions;

import com.employeeservice.model.Employee;
import com.employeeservice.model.Order;
import com.employeeservice.model.Region;
import com.employeeservice.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import java.util.List;

public class OrderAction extends ActionSupport implements Preparable {

    private Order order;
    private OrderService service = new OrderService();
    private List<Employee> employees;
    private List<Region> regions;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String addOrder() throws Exception {
        service.addOrder(getOrder());
        return SUCCESS;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public void prepare() throws Exception {
        setEmployees(service.getAllEmployees());
        setRegions(service.getAllRegions());
    }
}
