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

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "teacher")
    private String teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
