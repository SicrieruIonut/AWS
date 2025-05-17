// src/main/java/com/sicrieruionut/demo1/jsf/EnrollmentBean.java
package com.sicrieruionut.demo1.jsf;

import com.sicrieruionut.demo1.ejb.CourseService;
import com.sicrieruionut.demo1.ejb.EnrollmentService;
import com.sicrieruionut.demo1.ejb.StudentService;
import com.sicrieruionut.demo1.model.Course;
import com.sicrieruionut.demo1.model.Enrollment;
import com.sicrieruionut.demo1.model.EnrollmentId;
import com.sicrieruionut.demo1.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named("enrollmentBean")
@RequestScoped
public class EnrollmentBean {

    @EJB
    private EnrollmentService enrollmentService;
    @EJB
    private StudentService   studentService;
    @EJB
    private CourseService    courseService;

    private List<Enrollment> enrollments;
    private List<Student>    students;
    private List<Course>     courses;

    private Long selectedStudentId;
    private Long selectedCourseId;

    @PostConstruct
    public void init() {
        enrollments = enrollmentService.listAll();
        students    = studentService.findAll();
        courses     = courseService.findAll();
    }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public List<Student>    getStudents()    { return students;    }
    public List<Course>     getCourses()     { return courses;     }

    public Long getSelectedStudentId()       { return selectedStudentId; }
    public void setSelectedStudentId(Long id){ this.selectedStudentId = id; }

    public Long getSelectedCourseId()        { return selectedCourseId; }
    public void setSelectedCourseId(Long id) { this.selectedCourseId = id; }

    public void enroll() {
        if (selectedStudentId != null && selectedCourseId != null) {
            enrollmentService.createEnrollment(selectedStudentId, selectedCourseId);
            // reîncarcă lista și resetează selecțiile
            enrollments       = enrollmentService.listAll();
            selectedStudentId = null;
            selectedCourseId  = null;
        }
    }

    public void delete(EnrollmentId id) {
        enrollmentService.deleteEnrollment(id);
        enrollments = enrollmentService.listAll();
    }
}
