package com.project.system.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@Slf4j
@Entity
@Table(name = "employee_staff")
public class EmployeeStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_staff_id_gen")
    @SequenceGenerator(name = "employee_staff_id_gen", sequenceName = "employee_staff_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @Column(name = "date_begin")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;
}
