package com.example.database.crud.controller

import com.example.database.crud.Employee
import com.example.database.crud.repository.EmployeeRepository
import com.fasterxml.jackson.core.JsonEncoding
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/api/")
class EmployeeController(private val employeeRepository: EmployeeRepository) {
    //test
    @ModelAttribute("employees")
    fun employees(): List<Employee> {
        val iter : Iterable<Employee> = employeeRepository.findAll()
        val iterator : Iterator<Employee> = iter.iterator()
        val list : List<Employee> = iterator.asSequence().toList()
        return list
    }

    @RequestMapping(value = "employees", method = [RequestMethod.GET])
    fun employes(): ModelAndView {
        val mav = ModelAndView("employeesList")
        mav.addObject("employees", employeeRepository.findAll())
        return mav
    }

    /*@GetMapping("")
    fun getAllEmployyes(): Iterable<Employee> = employeeRepository.findAll()*/

    @GetMapping("")
    fun getAllEmployyes(model : Model): String{
        model.addAttribute("employees", employeeRepository.findAll())
        return "employeesList"
    }

    //@PostMapping("/create")
    @RequestMapping(value = "create", method = [RequestMethod.GET])
    /*fun createNewEmployee(@Valid @RequestBody employee: Employee): Employee =
            employeeRepository.save(employee)*/
    fun createNewEmployee(@ModelAttribute("employee") employee : Employee): String{
        employeeRepository.save(employee)
        return "redirect:/api/employees"
    }


    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { employee ->
            ResponseEntity.ok(employee)
        }.orElse(ResponseEntity.notFound().build())
    }

    /*@PutMapping("/{id}")
    fun updateEmployeeById(@PathVariable(value = "id") employeeId: Long,
                          @Valid @RequestBody newEmployee: Employee): ResponseEntity<Employee> {

        return employeeRepository.findById(employeeId).map { existingEmployee ->
            val updatedEmployee: Employee = existingEmployee
                    .copy(first_name = newEmployee.first_name, last_name = newEmployee.last_name, year_of_birth = newEmployee.year_of_birth, year_of_hiring = newEmployee.year_of_hiring)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())

    }*/
    @RequestMapping(value = "update/{id}")
    fun updateEmployeeById(@PathVariable(value = "id") employeeId: Long, model: Model) : String {
        model.addAttribute("employee", employeeRepository.findById(employeeId))
        model.addAttribute("employeesList", employeeRepository.findAll())
        return "api/employees"

    }
    @RequestMapping(value = "remove/{id}")
    fun deleteEmployeeById(@PathVariable(value = "id") employeeId: Long): String{
        employeeRepository.deleteById(employeeId)
        return "redirect:/api/employees"
    }
}
