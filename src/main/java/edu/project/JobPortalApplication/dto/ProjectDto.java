package edu.project.JobPortalApplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {

	private long projectId;
    private String projectTitle;
    private String projectDescription;
    private String projectSiteURL;
}
