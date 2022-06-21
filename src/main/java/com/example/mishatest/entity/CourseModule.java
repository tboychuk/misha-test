package com.example.mishatest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "module")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
public class CourseModule {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "courseModule", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Task> tasks = new HashSet<>();

    public CourseModule(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
        task.setCourseModule(this);
        this.tasks.add(task);
    }
}
