package com.bootcamp.emazon.user.domain.model;

public class Role {
    private Long idRole;
    private String name;
    private String description;

    public Role(Long idRole, String name, String description) {
        this.idRole = idRole;
        this.name = name;
        this.description = description;
    }

    public Role(){}

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long id) {
        this.idRole = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
