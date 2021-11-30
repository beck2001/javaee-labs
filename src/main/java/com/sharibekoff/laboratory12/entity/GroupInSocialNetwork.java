package com.sharibekoff.laboratory12.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GroupInSocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "group name must be specified")
    private String groupName;
    @PositiveOrZero(message = "number of subscribers can not be negative")
    private Integer numberOfSubscribers;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate createdAt;
//    @ManyToMany(mappedBy = "groups")
//    Set<User> users;

    @PrePersist
    private void init() {
        setCreatedAt(LocalDate.now());
    }

    public GroupInSocialNetwork(String groupName, Integer numberOfSubscribers, LocalDate createdAt) {
        this.groupName = groupName;
        this.numberOfSubscribers = numberOfSubscribers;
        this.createdAt = createdAt;
    }
}
