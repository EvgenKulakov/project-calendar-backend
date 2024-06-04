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
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "link_to_gitlab")
    private String linkToGitlab;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "is_deleted" , nullable = false)
    private Boolean isDeleted;


    public enum Status {
        ACTIVE,
        COMPLETED;
    }
}
