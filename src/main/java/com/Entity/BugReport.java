package com.Entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bug_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BugReport {

    // Id autogeneration
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the bug report
    @Column(nullable = true, length = 100)
    private String title;

    // Status of the bug report
    @Column(nullable = true, length = 50)
    private String status;

    // Project associated with the bug report
    @Column(nullable = true, length = 100)
    private String project;

    // Type of the bug report
    @Column(nullable = true, length = 50)
    private String type;

    // Name of the reporter
    @Column(nullable = true, length = 100)
    private String reporterName;

    // Bug created timestamp
    @Column(nullable = true, length = 100)
    private String createdAt;

    // Bug updated timestamp
    @Column(nullable = true, length = 100)
    private String updatedAt;

    // Bug created timestamp
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now().toString();
    }

    // Bug updated timestamp
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now().toString();
    }

}
