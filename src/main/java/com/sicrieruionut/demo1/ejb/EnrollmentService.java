// src/main/java/com/sicrieruionut/demo1/ejb/EnrollmentService.java
package com.sicrieruionut.demo1.ejb;

import com.sicrieruionut.demo1.model.Course;
import com.sicrieruionut.demo1.model.Enrollment;
import com.sicrieruionut.demo1.model.EnrollmentId;
import com.sicrieruionut.demo1.model.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EnrollmentService {

    @PersistenceContext
    private EntityManager em;

    /** Listează toate înscrierile */
    public List<Enrollment> listAll() {
        return em.createQuery("SELECT e FROM Enrollment e", Enrollment.class)
                .getResultList();
    }

    /** Creează o nouă înscriere, setând corect EmbeddedId-ul */
    public void createEnrollment(Long studentId, Long courseId) {
        Student s = em.find(Student.class, studentId);
        Course  c = em.find(Course.class, courseId);
        if (s != null && c != null) {
            Enrollment e = new Enrollment(s, c);
            em.persist(e);
        }
    }

    /** Șterge înscrierea cu cheia compusă dată */
    public void deleteEnrollment(EnrollmentId id) {
        Enrollment e = em.find(Enrollment.class, id);
        if (e != null) {
            em.remove(e);
        }
    }
}
