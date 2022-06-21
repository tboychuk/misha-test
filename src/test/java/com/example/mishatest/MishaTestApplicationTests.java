package com.example.mishatest;

import com.example.mishatest.entity.CourseModule;
import com.example.mishatest.entity.Task;
import com.example.mishatest.repo.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MishaTestApplicationTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TaskRepo taskRepo;

    @Test
    void addTaskViaHelperMethod() {
        var module = givenStoredModule("JPA and Hibernate");
        var newTask = new Task("Read an article on JPA one to many mapping");
        
        module.addTask(newTask);
        entityManager.flush();
        entityManager.clear();

        var storedTask = taskRepo.findById(newTask.getId()).orElseThrow();
        assertThat(storedTask).isNotNull();
        assertThat(storedTask).isEqualTo(newTask);
        assertThat(storedTask.getCourseModule()).isEqualTo(module);
    }

    @Test
    void addTaskViaGetter() {
        var module = givenStoredModule("JPA and Hibernate");
        var newTask = new Task("Read an article on JPA one to many mapping");

        module.getTasks().add(newTask);
        entityManager.flush();
        entityManager.clear();

        var storedTask = taskRepo.findById(newTask.getId()).orElseThrow();
        assertThat(storedTask).isNotNull();
        assertThat(storedTask).isEqualTo(newTask);
        assertThat(storedTask.getCourseModule()).isEqualTo(module);
    }

    CourseModule givenStoredModule(String moduleName) {
        var module = new CourseModule(moduleName);
        return entityManager.persistAndFlush(module);
    }

}
