package com.sharibekoff.laboratory12.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "first name shouldn't be empty")
    private String firstName;
    @NotEmpty(message = "last name shouldn't be empty")
    private String lastName;
    @NotNull(message = "birth date must be set")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotEmpty(message = "city should be specified")
    private String city;
    @Positive(message = "age should be positive")
    private Integer age;
    private String status;
//    @ManyToMany
//    @JoinTable(
//            name = "User_Group",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id")
//    )
//    Set<GroupInSocialNetwork> groups;

    public User(String firstName, String lastName, LocalDate birthDate, String city, Integer age, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.age = age;
        this.status = status;
    }
}
