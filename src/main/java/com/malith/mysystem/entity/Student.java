package com.malith.mysystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_customer_email",
                        columnNames = "email"
                )
        }
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private long studentId;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    private int age;
}
