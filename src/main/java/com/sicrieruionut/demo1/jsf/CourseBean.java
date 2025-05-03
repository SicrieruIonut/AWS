package com.sicrieruionut.demo1.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.ejb.EJB;
import com.sicrieruionut.demo1.ejb.CourseService;
import com.sicrieruionut.demo1.model.Course;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CourseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB private CourseService svc;
    private Course course = new Course();
    private List<Course> courses;

    public List<Course> getCourses() {
        if (courses == null) courses = svc.listAll();
        return courses;
    }

    public void create() {
        svc.create(course);
        course = new Course();
        courses = svc.listAll();
    }

    public void update(Course c) {
        svc.update(c);
        courses = svc.listAll();
    }

    public void delete(Long id) {
        svc.delete(id);
        courses = svc.listAll();
    }

    // getters & setters
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
