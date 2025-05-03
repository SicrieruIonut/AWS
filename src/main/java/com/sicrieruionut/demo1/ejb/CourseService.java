package com.sicrieruionut.demo1.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.sicrieruionut.demo1.model.Course;
import java.util.List;

@Stateless
public class CourseService {
    @PersistenceContext private EntityManager em;

    public Course create(Course c) { em.persist(c); return c; }
    public Course update(Course c) { return em.merge(c); }
    public void delete(Long id) { em.remove(em.find(Course.class, id)); }
    public Course find(Long id) { return em.find(Course.class, id); }
    public List<Course> listAll() {
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }
}
