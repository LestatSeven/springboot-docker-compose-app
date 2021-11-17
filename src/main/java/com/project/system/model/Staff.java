package com.project.system.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Slf4j
@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staffs_id_gen")
    @SequenceGenerator(name = "staffs_id_gen", sequenceName = "staffs_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "max_employee_count")
    private Integer maxEmployeeCount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "employee_staff",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;
}
