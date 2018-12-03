package com.example.database.crud

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name="employeeinfo")
data class Employee (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

        var Eid: Long = 0,

        @get: NotBlank
        var first_name: String = "",

        @get: NotBlank
        var last_name: String = "",

        @get: NotBlank
        var year_of_birth: Int = 0,

        @get: NotBlank
        var year_of_hiring: Int = 0,

        @get: NotBlank
        var manager_id: Long = 0
)
