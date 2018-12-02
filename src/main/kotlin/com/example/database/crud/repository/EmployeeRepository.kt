package com.example.database.crud.repository

import com.example.database.crud.Employee

import com.example.database.crud.repository.EmployeeRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long>