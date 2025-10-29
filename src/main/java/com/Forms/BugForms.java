package com.Forms;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BugForms {

    // Title of the bug reporrt
    @NotBlank(message = "Title is required")
    private String title;

    // status of the bug report
    @NotBlank(message = "Status is required")
    private String status;

    // project associated with the bug report
    @NotBlank(message = "Project associated is required")
    private String project;

    // Type of the bug report
    @NotBlank(message = "Bug Type is required")
    private String type;

    // Name of the reporter
    @NotBlank(message = "Reporter Name is required")
    private String reporterName;

}
