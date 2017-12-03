package com.employeeservice.service;

import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeService {

    private static final String WORD = "Struts";
    private EmployeeRepository repository;

    public EmployeeService() {
        this.repository = new EmployeeRepository();
    }

    public Employee authenticate(Employee employee) {
        Employee storedEmployee = repository.getEmployeeByLogin(employee.getLogin());
        if (storedEmployee != null) {
            String hashedPassword = generateHash(employee.getPassword());
            String storedPassword = storedEmployee.getPassword();
            return hashedPassword.equals(storedPassword) ? storedEmployee : null;
        }
        return null;
    }

    public Employee getEmployee(String login) {
        return repository.getEmployeeByLogin(login);
    }

    public boolean saveEmployee(Employee employee) {
        employee.setPassword(generateHash(employee.getPassword()));
        return repository.save(employee);
    }

    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        input = WORD + input;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash.toString();
    }
}
