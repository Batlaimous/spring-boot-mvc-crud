package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    // add mapping fo0r "/List"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam("employeeId") int theEmployeeId, Model theModel) {
        Employee theEmployee = employeeService.findById(theEmployeeId);
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theEmployeeId) {
        employeeService.deleteById(theEmployeeId);
        return "redirect:/employees/list";
    }


}
