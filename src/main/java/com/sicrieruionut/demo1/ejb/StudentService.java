package com.sicrieruionut.demo1.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.sicrieruionut.demo1.model.Student;
import java.util.List;

@Stateless
public class StudentService {
    @PersistenceContext private EntityManager em;

    public Student create(Student s) { em.persist(s); return s; }
    public Student update(Student s) { return em.merge(s); }
    public void delete(Long id) { em.remove(em.find(Student.class, id)); }
    public Student find(Long id) { return em.find(Student.class, id); }
    public List<Student> listAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }
}

