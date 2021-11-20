package com.project.system.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    private Profession profession;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Staff staff = (Staff) o;
        return id != null && Objects.equals(id, staff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
