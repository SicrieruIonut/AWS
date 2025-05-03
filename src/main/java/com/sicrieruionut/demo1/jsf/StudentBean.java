package com.sicrieruionut.demo1.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.ejb.EJB;
import com.sicrieruionut.demo1.ejb.StudentService;
import com.sicrieruionut.demo1.model.Student;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private StudentService svc;

    private Student student = new Student();
    private List<Student> students;

    // Încarcă lista de studenți
    public void loadAll() {
        students = svc.listAll();
    }

    // Creează un student nou și reîncarcă lista
    public void create() {
        svc.create(student);
        student = new Student();
        loadAll();
    }

    // Actualizează un student și reîncarcă lista
    public void update(Student s) {
        svc.update(s);
        loadAll();
    }

    // Șterge după id și reîncarcă lista
    public void delete(Long id) {
        svc.delete(id);
        loadAll();
    }

    // Lazy-loading: dacă nu e încărcată, o încarcă
    public List<Student> getStudents() {
        if (students == null) {
            loadAll();
        }
        return students;
    }

    // Getters & Setters pentru binding JSF
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}