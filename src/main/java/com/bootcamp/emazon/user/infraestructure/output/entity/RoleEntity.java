package com.bootcamp.emazon.user.infraestructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity {
    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;
}
