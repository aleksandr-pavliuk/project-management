package ua.org.pma.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ua.org.pma.dao.EmployeeRepository;
import ua.org.pma.entity.Employee;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    System.out.println("Entered validation method..");

    Employee emp = employeeRepository.findByEmail(value);

    if (emp != null) {
      return false;
    } else {
      return true;
    }

  }

}
