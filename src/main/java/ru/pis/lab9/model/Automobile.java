package ru.pis.lab9.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "table_automobile", schema = "lab9_schema")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    @Column(name = "gos_number")
    private String gosNumber;
    private Integer year;
    private Integer consumption;
    @Column(name = "total_consumed")
    private Integer totalConsumed;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "table_driver",
            joinColumns = {@JoinColumn(name = "automobile")},
            inverseJoinColumns = {@JoinColumn(name = "employee")})
    private List<Employee> employees;
}
