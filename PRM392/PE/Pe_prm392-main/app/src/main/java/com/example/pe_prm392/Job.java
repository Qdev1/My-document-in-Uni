package com.example.pe_prm392;

public class Job {
    private String Id;
    private String name;
    private String status;
    private String description;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job(String id, String name, String status, String description) {
        Id = id;
        this.name = name;
        this.status = status;
        this.description = description;
    }
}
