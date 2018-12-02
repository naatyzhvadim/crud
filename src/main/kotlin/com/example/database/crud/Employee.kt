package com.example.database.crud

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name="employeeinfo")
data class Employee (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

        val Eid: Long = 0,

        @get: NotBlank
        val first_name: String = "",

        @get: NotBlank
        val last_name: String = "",

        @get: NotBlank
        val year_of_birth: Int = 0,

        @get: NotBlank
        val year_of_hiring: Int = 0,

        @get: NotBlank
        val manager_id: Long = 0
)
