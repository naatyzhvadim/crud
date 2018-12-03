package com.example.database.crud.repository

import com.example.database.crud.Employee

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long>