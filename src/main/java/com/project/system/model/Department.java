package com.project.system.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Slf4j
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_id_gen")
    @SequenceGenerator(name = "departments_id_gen", sequenceName = "departments_id_seq", allocationSize = 1)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    Department parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    List<Department> children;

    @Column(name = "name")
    String name;

    public Department getParent() {
        return parent;
    }
}
