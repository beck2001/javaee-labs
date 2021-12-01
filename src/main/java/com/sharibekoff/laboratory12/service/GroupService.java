package com.sharibekoff.laboratory12.service;

import com.sharibekoff.laboratory12.entity.GroupInSocialNetwork;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class GroupService {

    @PersistenceContext
    EntityManager entityManager;

    public GroupInSocialNetwork createGroup(GroupInSocialNetwork groupInSocialNetwork) {
        entityManager.persist(groupInSocialNetwork);
        return groupInSocialNetwork;
    }

    public GroupInSocialNetwork updateGroup(GroupInSocialNetwork groupInSocialNetwork) {
        entityManager.merge(groupInSocialNetwork);
        return groupInSocialNetwork;
    }

    public GroupInSocialNetwork findById(Long id) {
        return entityManager.find(GroupInSocialNetwork.class, id);
    }

    public List<GroupInSocialNetwork> findAll() {
        return entityManager.createQuery("SELECT g FROM GroupInSocialNetwork g", GroupInSocialNetwork.class).getResultList();
    }

    public List<GroupInSocialNetwork> findBySubscribersAmount(int amount) {
        return findAll().stream()
                .filter(groupInSocialNetwork -> groupInSocialNetwork.getNumberOfSubscribers() > amount)
                .collect(Collectors.toList());
    }

    public GroupInSocialNetwork deleteGroup(GroupInSocialNetwork groupInSocialNetwork) {
        if (!entityManager.contains(groupInSocialNetwork)) {
            groupInSocialNetwork = entityManager.merge(groupInSocialNetwork);
        }
        entityManager.remove(groupInSocialNetwork);
        return groupInSocialNetwork;
    }
}
