package com.project.system.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Slf4j
@Entity
@Table(name = "profession_titles")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profession_titles_id_gen")
    @SequenceGenerator(name = "profession_titles_id_gen", sequenceName = "profession_titles_id_seq", allocationSize = 1)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;
}
