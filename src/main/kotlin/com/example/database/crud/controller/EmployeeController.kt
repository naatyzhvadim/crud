package com.example.database.crud.controller

import com.example.database.crud.Employee
import com.example.database.crud.repository.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class EmployeeController(private val employeeRepository: EmployeeRepository) {

    @GetMapping("/employees")
    fun getAllEmployyes(): List<Employee> = employeeRepository.findAll()


    @PostMapping("/employees")
    fun createNewEmployee(@Valid @RequestBody employee: Employee): Employee =
            employeeRepository.save(employee)


    @GetMapping("/employees/{id}")
    fun getEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { employee ->
            ResponseEntity.ok(employee)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/employees/{id}")
    fun updateEmployeeById(@PathVariable(value = "id") employeeId: Long,
                          @Valid @RequestBody newEmployee: Employee): ResponseEntity<Employee> {

        return employeeRepository.findById(employeeId).map { existingEmployee ->
            val updatedEmployee: Employee = existingEmployee
                    .copy(first_name = newEmployee.first_name, last_name = newEmployee.last_name, year_of_birth = newEmployee.year_of_birth, year_of_hiring = newEmployee.year_of_hiring)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())
        
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Void> {

        return employeeRepository.findById(employeeId).map { employee  ->
            employeeRepository.delete(employee)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}
