package com.example.ContactsManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @Column(length=10)
    private String id;
    private String name;
    //
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}
