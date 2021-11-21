package com.project.reporting.reporting.collector;

import com.project.reporting.reporting.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class EmployeesDatabaseDataCollectorImpl<T> implements DatabaseDataCollector<Employee> {
    private JdbcTemplate jdbcTemplate;
    private String query;
    private RowMapper<Employee> employeeRowMapper;
    private List<Employee> data;

    public EmployeesDatabaseDataCollectorImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void collect() {
        this.query = """
            select
                e.id,
                e.last_name || ' ' || e.first_name || ' ' || e.middle_name as full_name,
                e.email,
                e.phone_number,
                p.name as current_job,
                d.name as dep_name
            from employees e
            join employee_staff es
              on es.employee_id = e.id
             and es.date_begin <= now()
             and coalesce(es.date_end, to_date('31.12.9999', 'dd.mm.yyyy')) >= now()
            join staffs s
              on s.id = es.staff_id
            join profession_titles p on p.id = s.profession_id
            join departments d on d.id = s.department_id
            order by dep_name, full_name
         """;
        this.employeeRowMapper = (ResultSet resultSet, int rowNum) -> {
            return new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("email"),
                    resultSet.getString("current_job"),
                    resultSet.getString("dep_name")
            );
        };
        this.collect(this.query, this.employeeRowMapper);
    }

    @Override
    public void collect(String query, RowMapper<Employee> mapper) {
        data = jdbcTemplate.query(query, employeeRowMapper);
        //data.forEach(employee -> System.out.println(employee));
    }

    @Override
    public List<Employee> getData() {
        return data;
    }
}
