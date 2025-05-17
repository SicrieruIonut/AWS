// src/main/java/com/sicrieruionut/demo1/jsf/CourseBean.java
package com.sicrieruionut.demo1.jsf;

import com.sicrieruionut.demo1.ejb.CourseService;
import com.sicrieruionut.demo1.model.Course;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CourseBean {

    @EJB
    private CourseService service;

    private List<Course> courses;
    private Course course = new Course();

    @PostConstruct
    public void init() {
        courses = service.findAll();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public void create() {
        service.createCourse(course);
        courses = service.findAll();
        course = new Course();
    }

    public void update(Course c) {
        service.updateCourse(c);
        courses = service.findAll();
    }

    public void delete(Long id) {
        service.deleteCourse(id);
        courses.removeIf(x -> x.getId().equals(id));
    }
}
