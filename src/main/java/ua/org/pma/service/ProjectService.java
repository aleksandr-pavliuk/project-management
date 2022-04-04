package ua.org.pma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.pma.dao.ProjectRepository;
import ua.org.pma.dto.ChartData;
import ua.org.pma.dto.TimeChartData;
import ua.org.pma.entity.Project;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Service
public class ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  public Project save(Project project) {
    return projectRepository.save(project);
  }

  public List<Project> getAll() {
    return projectRepository.findAll();
  }

  public List<ChartData> getProjectStatus() {
    return projectRepository.getProjectStatus();
  }

  public List<TimeChartData> getTimeData() {
    return projectRepository.getTimeData();
  }
}
