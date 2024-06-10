package com.its.samara.project.calendar.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "current_stage_id")
    private Integer currentStageId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "employee_ids", columnDefinition = "int[]")
    private int[] employeeIds;

    @Column(name = "estimated_hours")
    private Integer estimatedHours;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;


    public enum Status {
        CREATED,
        IN_PROGRESS,
        EXPIRED,
        FINISH;
    }
}
