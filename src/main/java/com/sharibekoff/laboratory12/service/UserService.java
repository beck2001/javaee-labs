package com.sharibekoff.laboratory12.service;

import com.sharibekoff.laboratory12.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public User createUser(User user) {
        // persist into db
        entityManager.persist(user);
        return user;
    }

    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<User> findByAge(int age) {
        return findAll().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

//    public User deleteUser(User user) {
//        if (!entityManager.contains(user)) {
//            user = entityManager.merge(user);
//        }
//        entityManager.remove(user);
//        return user;
//    }
}
