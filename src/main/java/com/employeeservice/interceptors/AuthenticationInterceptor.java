package com.employeeservice.interceptors;

import com.employeeservice.model.Employee;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;
import java.util.Map;

public class AuthenticationInterceptor implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
        Employee employee = (Employee) sessionAttributes.get("user");
        sessionAttributes.put("target", ServletActionContext.getRequest().getRequestURI());
        return (employee == null) ? Action.LOGIN : actionInvocation.invoke();
    }

}
