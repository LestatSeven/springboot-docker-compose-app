package com.project.reporting.reporting.collector;

import com.project.reporting.reporting.model.StaffEmployee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class StaffEmployeesDataCollectorImpl implements DataCollector<StaffEmployee> {
    private final JdbcTemplate jdbcTemplate;
    private final String query;
    private final RowMapper<StaffEmployee> staffEmployeesRowMapper;

    public StaffEmployeesDataCollectorImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        query = """
            with count_employees as (
                select staff_id, count(*) as cnt
                from staffs s
                left join employee_staff es
                  on s.id = es.staff_id
                 and es.date_begin <= now()
                 and coalesce(es.date_end, to_date('31.12.9999', 'dd.mm.yyyy')) >= now()
                group by staff_id
            )
            
            select
                s.id,
                d.name as dep_name,
                pt.name as prof_name,
                ce.cnt as real_employee_count,
                s.max_employee_count as max_employee_count
            from staffs s
            left join departments d
              on d.id = s.department_id
            left join profession_titles pt
              on pt.id = s.profession_id
            left join count_employees ce
              on ce.staff_id = s.id
            order by  d.name asc, pt.name asc
         """;

        staffEmployeesRowMapper = (ResultSet resultSet, int rowNum) -> {
            System.out.println(resultSet.getInt("real_employee_count"));
            return new StaffEmployee(
                    resultSet.getInt("id"),
                    resultSet.getString("dep_name"),
                    resultSet.getString("prof_name"),
                    resultSet.getInt("real_employee_count"),
                    resultSet.getInt("max_employee_count")
            );
        };
    }

    public List<StaffEmployee> getData() {
        return jdbcTemplate.query(query, staffEmployeesRowMapper);
    }
}
