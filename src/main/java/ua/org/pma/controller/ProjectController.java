package ua.org.pma.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.org.pma.dao.EmployeeRepository;
import ua.org.pma.dao.ProjectRepository;
import ua.org.pma.dto.TimeChartData;
import ua.org.pma.entity.Employee;
import ua.org.pma.entity.Project;
import ua.org.pma.service.EmployeeService;
import ua.org.pma.service.ProjectService;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {

  @Autowired
  ProjectService projectService;

  @Autowired
  EmployeeService employeeService;

  @GetMapping
  public String displayProjects(Model model){
    List<Project> projects = projectService.getAll();
    model.addAttribute("projects", projects);
    return "/projects/list-projects";
  }

  @GetMapping("/new")
  public String displayProjectForm(Model model) {
    Project project = new Project();
    Iterable<Employee> employees = employeeService.getAll();
    model.addAttribute("project", project);
    model.addAttribute("allEmployees", employees);
    return "projects/new-project";
  }

  @PostMapping("/save")
  public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
    projectService.save(project);
    return "redirect:/projects/new";
  }

  @GetMapping("/timelines")
  public String displayProjectTimelines(Model model) throws JsonProcessingException {

    List<TimeChartData> timelineData = projectService.getTimeData();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

    System.out.println("---------- project timelines ----------");
    System.out.println(jsonTimelineString);

    model.addAttribute("projectTimeList", jsonTimelineString);

    return "projects/project-timelines";
  }

}
