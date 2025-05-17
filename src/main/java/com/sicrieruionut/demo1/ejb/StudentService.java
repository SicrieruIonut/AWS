// src/main/java/com/sicrieruionut/demo1/ejb/StudentService.java
package com.sicrieruionut.demo1.ejb;

import com.sicrieruionut.demo1.model.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    /** Returnează toți studenții */
    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    /** Găsește un student după id */
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    /** Creează un student nou */
    public Student createStudent(Student student) {
        em.persist(student);
        return student;
    }

    /** Actualizează un student existent */
    public Student updateStudent(Student student) {
        return em.merge(student);
    }

    /** Șterge studentul cu id-ul dat (cascade Enrollment) */
    public void deleteStudent(Long id) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            em.remove(s);
        }
    }
}
