package com.example.mishatest.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "description")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    
    private String description;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private CourseModule courseModule;

    public Task(String description) {
        this.description = description;
    }
}
