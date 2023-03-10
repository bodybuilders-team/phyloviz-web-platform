package org.isel.phylovizwebplatform.gateway.repository.project;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Project {
    @Id
    private String id;

    private final String name;
    private final String description;
    private final String owner;
    private final List<File> files;

    public Project(String name, String description, String owner, List<File> files) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public List<File> getFiles() {
        return files;
    }
}
