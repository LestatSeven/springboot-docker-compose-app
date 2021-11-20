package com.project.system.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBegin;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeStaff that = (EmployeeStaff) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
