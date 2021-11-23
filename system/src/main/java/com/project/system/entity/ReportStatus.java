package com.project.system.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
@Entity
@Table(name = "reports_status")
public class ReportStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reports_status_id_gen")
    @SequenceGenerator(name = "reports_status_id_gen", sequenceName = "reports_status_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "reporting_config_id", referencedColumnName = "id")
    private ReportingConfig config;

    @Column(name = "generated_name")
    private String generatedName;

    @Column(name = "date_request")
    private LocalDateTime dateRequest;

    @Column(name = "date_received")
    private LocalDateTime dateReceived;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "error")
    private String error;
}
