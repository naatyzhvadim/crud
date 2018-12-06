package com.example.database.crud

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name="employeeinfo")
data class Employee (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Eid")
        var Eid: Long = 0,
        @Column(name = "first_name")
        var first_name: String = "",

        @Column(name = "last_name")
        var last_name: String = "",

        @Column(name = "year_of_birth")
        var year_of_birth: Int = 0,

        @Column(name = "year_of_hiring")
        var year_of_hiring: Int = 0,

        @Column(name = "manager_id")
        var manager_id: Long = 1
)
