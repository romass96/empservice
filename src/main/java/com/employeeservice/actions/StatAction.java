package com.employeeservice.actions;

import com.employeeservice.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;
import java.util.Map;

public class StatAction extends ActionSupport {
    private Date minDate;
    private Date maxDate;
    private Map<String, Integer> ordersCountMap;
    private Map<String, Map<String, Integer>> sumPrices;
    private OrderService orderService = new OrderService();


    @Override
    public String execute() throws Exception {
        Map<String, Date> dateMap = orderService.getMinAndMaxOrderDate();
        setMinDate(dateMap.get("min"));
        setMaxDate(dateMap.get("max"));
        setSumPrices(orderService.getAllSumPrices());
        return SUCCESS;
    }

    public String sendOrdersCount() throws Exception {
        setOrdersCountMap(orderService.getOrdersCountByDateRange(minDate, maxDate));
        return SUCCESS;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Map<String, Integer> getOrdersCountMap() {
        return ordersCountMap;
    }

    public void setOrdersCountMap(Map<String, Integer> ordersCountMap) {
        this.ordersCountMap = ordersCountMap;
    }

    public Map<String, Map<String, Integer>> getSumPrices() {
        return sumPrices;
    }

    public void setSumPrices(Map<String, Map<String, Integer>> sumPrices) {
        this.sumPrices = sumPrices;
    }

}
