package com.sharibekoff.laboratory12.entity;

import java.time.LocalDate;

public class GroupBuilderImpl implements GroupBuilder {
    GroupInSocialNetwork group = new GroupInSocialNetwork();

    @Override
    public GroupBuilder id(Long id) {
        group.setId(id);
        return this;
    }

    @Override
    public GroupBuilder groupName(String gName) {
        group.setGroupName(gName);
        return this;
    }

    @Override
    public GroupBuilder subscribersAmount(Integer numberOfSubscribers) {
        group.setNumberOfSubscribers(numberOfSubscribers);
        return this;
    }

    @Override
    public GroupBuilder dateCreated(LocalDate createdAt) {
        group.setCreatedAt(createdAt);
        return this;
    }

    @Override
    public GroupInSocialNetwork build() {
        return group;
    }
}
