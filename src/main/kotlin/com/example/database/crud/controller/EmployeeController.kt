package com.example.database.crud.controller

import com.example.database.crud.Employee
import com.example.database.crud.repository.EmployeeRepository
import com.fasterxml.jackson.core.JsonEncoding
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.ModelAndView

@Controller
class EmployeeController(private val employeeRepository: EmployeeRepository) {

    @RequestMapping(value = "/")
    fun showPage(model:Model,@RequestParam(defaultValue = "0") page : Int) : String{
        model.addAttribute("employees", employeeRepository.findAll(PageRequest.of(page, 5)))
        model.addAttribute("currentPage", page)
        return "employeesList"
    }

    @RequestMapping(value = "/create", method = [RequestMethod.POST])
    fun createNewEmployee(@ModelAttribute("employee") employee : Employee): String{
        employeeRepository.save(employee)
        return "redirect:/"
    }

    @RequestMapping(value = "/update/{id}", method = [RequestMethod.POST])
    fun updateEmployeeById(@PathVariable(value = "id") employeeId: Long, newEmployee: Employee) : String {
        employeeRepository.findById(employeeId).map { existingEmployee ->
            val updatedEmployee: Employee = existingEmployee
                    .copy(first_name = newEmployee.first_name, last_name = newEmployee.last_name, year_of_birth = newEmployee.year_of_birth, year_of_hiring = newEmployee.year_of_hiring, manager_id = newEmployee.manager_id)
            employeeRepository.save(updatedEmployee)
        }
        return "redirect:/"
    }

    @RequestMapping(value = "/remove/{id}")
    fun deleteEmployeeById(@PathVariable(value = "id") employeeId: Long, @RequestParam(defaultValue = "0") page : Int): String{
        employeeRepository.deleteById(employeeId)
        return "redirect:/"
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { employee ->
            ResponseEntity.ok(employee)
        }.orElse(ResponseEntity.notFound().build())
    }

    /*@ModelAttribute("employees")
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

    @GetMapping("")
    fun getAllEmployyes(): Iterable<Employee> = employeeRepository.findAll()

    @GetMapping("/list")
    fun getAllEmployyes(model : Model): String{
        model.addAttribute("employees", employeeRepository.findAll())
        return "employeesList"
    }*/
}
