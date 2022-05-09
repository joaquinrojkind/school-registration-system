package com.metadata.registration.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "teacher", nullable = false)
    private String teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
