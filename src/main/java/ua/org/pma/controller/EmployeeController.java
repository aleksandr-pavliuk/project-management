package ua.org.pma.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.org.pma.entity.Employee;
import ua.org.pma.service.EmployeeService;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @GetMapping
  public String displayEmployees(Model model){
    Iterable<Employee> employees = employeeService.getAll();
    model.addAttribute("employees", employees);
    return "/employees/list-employees";
  }

  @GetMapping("/new")
  public String displayEmployeeForm(Model model) {
    Employee employee = new Employee();
    model.addAttribute("employee", employee);
    return "employees/new-employee";
  }

  @PostMapping("/save")
  public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

    if(errors.hasErrors())
      return "employees/new-employee";

    // save to the database using an employee crud repository
    employeeService.save(employee);

    return "redirect:/employees";
  }

  @GetMapping("/update")
  public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {

    Employee employee = employeeService.findByEmployeeId(theId);

    model.addAttribute("employee", employee);


    return "employees/new-employee";
  }

  @GetMapping("delete")
  public String deleteEmployee(@RequestParam("id") long theId, Model model) {
    Employee employee = employeeService.findByEmployeeId(theId);
    employeeService.delete(employee);
    return "redirect:/employees";
  }
}
