// src/main/java/com/sicrieruionut/demo1/jsf/StudentBean.java
package com.sicrieruionut.demo1.jsf;

import com.sicrieruionut.demo1.ejb.StudentService;
import com.sicrieruionut.demo1.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class StudentBean {

    @EJB
    private StudentService service;

    private List<Student> students;
    private Student student = new Student();

    @PostConstruct
    public void init() {
        students = service.findAll();
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public void create() {
        service.createStudent(student);
        students = service.findAll();
        student = new Student();
    }

    public void update(Student s) {
        service.updateStudent(s);
        students = service.findAll();
    }

    public void delete(Long id) {
        service.deleteStudent(id);
        students.removeIf(x -> x.getId().equals(id));
    }
}
