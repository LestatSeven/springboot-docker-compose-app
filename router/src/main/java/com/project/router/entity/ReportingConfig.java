package com.project.router.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Slf4j
@Entity
@Table(name = "reporting_config")
public class ReportingConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reporting_config_id_gen")
    @SequenceGenerator(name = "reporting_config_id_gen", sequenceName = "reporting_config_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "report_url")
    private String reportUrl;
}
