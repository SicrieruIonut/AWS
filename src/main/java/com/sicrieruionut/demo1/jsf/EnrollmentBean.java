package com.sicrieruionut.demo1.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.ejb.EJB;
import com.sicrieruionut.demo1.ejb.EnrollmentService;
import com.sicrieruionut.demo1.ejb.StudentService;
import com.sicrieruionut.demo1.ejb.CourseService;
import com.sicrieruionut.demo1.model.Enrollment;
import com.sicrieruionut.demo1.model.Student;
import com.sicrieruionut.demo1.model.Course;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EnrollmentBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB private EnrollmentService enrollSvc;
    @EJB private StudentService studentSvc;
    @EJB private CourseService courseSvc;

    private Long selectedStudentId;
    private Long selectedCourseId;
    private List<Enrollment> enrollments;
    private List<Student> students;
    private List<Course> courses;

    public List<Enrollment> getEnrollments() {
        if (enrollments == null) enrollments = enrollSvc.listAll();
        return enrollments;
    }
    public List<Student> getStudents() {
        if (students == null) students = studentSvc.listAll();
        return students;
    }
    public List<Course> getCourses() {
        if (courses == null) courses = courseSvc.listAll();
        return courses;
    }

    public void enroll() {
        Student s = studentSvc.find(selectedStudentId);
        Course c = courseSvc.find(selectedCourseId);
        enrollSvc.create(new Enrollment(s, c));
        enrollments = enrollSvc.listAll();
    }

    public void delete(com.sicrieruionut.demo1.model.EnrollmentId id) {
        enrollSvc.delete(id);
        enrollments = enrollSvc.listAll();
    }

    // getters & setters for selectedStudentId/CourseId
    public Long getSelectedStudentId() { return selectedStudentId; }
    public void setSelectedStudentId(Long id) { this.selectedStudentId = id; }
    public Long getSelectedCourseId() { return selectedCourseId; }
    public void setSelectedCourseId(Long id) { this.selectedCourseId = id; }
}
