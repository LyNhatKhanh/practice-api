package com.lynhatkhanh.identity_service.entity;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    String name;

    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Permission> permissions;
}
