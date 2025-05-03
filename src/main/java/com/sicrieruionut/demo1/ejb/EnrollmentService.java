package com.sicrieruionut.demo1.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.sicrieruionut.demo1.model.Enrollment;
import com.sicrieruionut.demo1.model.EnrollmentId;
import java.util.List;

@Stateless
public class EnrollmentService {
    @PersistenceContext private EntityManager em;

    public Enrollment create(Enrollment e) { em.persist(e); return e; }
    public void delete(EnrollmentId id) {
        Enrollment e = em.find(Enrollment.class, id);
        if (e != null) em.remove(e);
    }
    public List<Enrollment> listAll() {
        return em.createQuery("SELECT e FROM Enrollment e", Enrollment.class)
                .getResultList();
    }
}
