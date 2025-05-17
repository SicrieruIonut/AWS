package com.sicrieruionut.demo1.ejb;

import com.sicrieruionut.demo1.entity.UserAccount;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.security.MessageDigest;
import java.util.Base64;

@Stateless
public class UserService {
    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public void register(String username, String rawPassword) throws Exception {
        UserAccount u = new UserAccount();
        u.setUsername(username);
        u.setPassword(hash(rawPassword));
        em.persist(u);
    }

    public UserAccount login(String username, String rawPassword) throws Exception {
        String hashed = hash(rawPassword);
        return em.createQuery(
                        "SELECT u FROM UserAccount u WHERE u.username = :u AND u.password = :p",
                        UserAccount.class)
                .setParameter("u", username)
                .setParameter("p", hashed)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    private String hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashed);
    }
}