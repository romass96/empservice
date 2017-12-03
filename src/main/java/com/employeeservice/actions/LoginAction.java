package com.employeeservice.actions;

import com.employeeservice.model.Employee;
import com.employeeservice.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware, ModelDriven<Employee> {

    private EmployeeService service = new EmployeeService();
    private Employee employee = new Employee();
    private Map<String, Object> sessionAttributes = null;
    private String target;

    @Override
    public String execute() throws Exception {
        Employee authEmployee = service.authenticate(employee);
        if (authEmployee != null) {
            this.employee = authEmployee;
            sessionAttributes.put("user", employee);
            String targetURL = (String) sessionAttributes.get("target");
            setTarget(targetURL);
            return targetURL != null ? "afterLogin" : SUCCESS;
        }
        addActionError("Неправильный логин или пароль");
        return INPUT;
    }

    @SkipValidation
    public String logout() throws Exception {
        sessionAttributes.remove("user");
        return "LOGOUT";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionAttributes = map;
    }

    @Override
    public Employee getModel() {
        return employee;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
