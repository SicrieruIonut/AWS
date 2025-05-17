// src/main/java/com/sicrieruionut/demo1/ejb/CourseService.java
package com.sicrieruionut.demo1.ejb;

import com.sicrieruionut.demo1.model.Course;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseService {

    @PersistenceContext
    private EntityManager em;

    /** Returnează toate cursurile */
    public List<Course> findAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultList();
    }

    /** Găsește un curs după id */
    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    /** Creează un curs nou */
    public Course createCourse(Course course) {
        em.persist(course);
        return course;
    }

    /** Actualizează un curs existent */
    public Course updateCourse(Course course) {
        return em.merge(course);
    }

    /** Șterge cursul cu id-ul dat (cascade Enrollment) */
    public void deleteCourse(Long id) {
        Course c = em.find(Course.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}
