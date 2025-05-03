package com.sicrieruionut.demo1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    // getter & setter pentru id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // getter & setter pentru name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // getter & setter pentru enrollments
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}