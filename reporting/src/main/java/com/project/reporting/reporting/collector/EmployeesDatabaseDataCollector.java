package com.project.reporting.reporting.collector;

import com.project.reporting.reporting.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class EmployeesDatabaseDataCollector<T> implements DatabaseDataCollector<Employee> {
    private JdbcTemplate jdbcTemplate;
    private String query;
    private RowMapper<Employee> employeeRowMapper;
    private List<Employee> data;

    public EmployeesDatabaseDataCollector(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void collect() {
        this.query = "select last_name || ' ' || first_name || ' ' || middle_name as full_name, email, phone_number from employees";
        this.employeeRowMapper = (ResultSet resultSet, int rowNum) -> {
            return new Employee(
                    resultSet.getString("full_name"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email")
            );
        };
        this.collect(this.query, this.employeeRowMapper);
    }

    @Override
    public void collect(String query, RowMapper<Employee> mapper) {
        data = jdbcTemplate.query(query, employeeRowMapper);
        //data.forEach(employee -> System.out.println(employee));
    }
}
