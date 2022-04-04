package ua.org.pma.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.org.pma.dao.EmployeeRepository;
import ua.org.pma.dao.ProjectRepository;
import ua.org.pma.dto.ChartData;
import ua.org.pma.dto.EmployeeProject;
import ua.org.pma.entity.Project;
import ua.org.pma.service.EmployeeService;
import ua.org.pma.service.ProjectService;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Controller
public class HomeController {

  @Value("${version}")
  private String ver;

  @Autowired
  ProjectService projectService;

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/")
  public String displayHome(Model model) throws JsonProcessingException {

    model.addAttribute("versionNumber", ver);

    List<Project> projects = projectService.getAll();
    model.addAttribute("projects", projects);

    List<ChartData> projectData = projectService.getProjectStatus();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(projectData);

    model.addAttribute("projectStatusCnt", jsonString);

    List<EmployeeProject> employeesListProjectsCnt = employeeService.employeeProjects();

    model.addAttribute("employeesListProjectsCnt", employeesListProjectsCnt);

    return "main/home";
  }
}
