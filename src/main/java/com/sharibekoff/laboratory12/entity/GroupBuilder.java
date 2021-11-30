package com.sharibekoff.laboratory12.entity;

import java.time.LocalDate;

public interface GroupBuilder {
    GroupBuilder id(Long id);
    GroupBuilder groupName(String gName);
    GroupBuilder subscribersAmount(Integer numberOfSubscribers);
    GroupBuilder dateCreated(LocalDate createdAt);
    GroupInSocialNetwork build();
}
