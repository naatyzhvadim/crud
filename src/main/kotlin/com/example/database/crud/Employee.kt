package com.example.database.crud

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name="employeeinfo")
data class Employee (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

        /*@Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "DN_ID", nullable = false, unique = true)*/

        val Eid: Long = 0,

        @get: NotBlank
        val first_name: String = "",

        @get: NotBlank
        val last_name: String = "",

        @get: NotBlank
        val year_of_birth: Int = 0,

        @get: NotBlank
        val year_of_hiring: Int = 0
)
