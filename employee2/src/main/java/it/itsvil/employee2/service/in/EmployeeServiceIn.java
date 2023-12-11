package it.itsvil.employee2.service.in;
import it.itsvil.employee2.dto.request.EmployeeRequestInsertDto;
import it.itsvil.employee2.dto.request.LoginRequestDto;
import it.itsvil.employee2.entity.Employee;

import java.util.List;

public interface EmployeeServiceIn {
    public Employee addEmployee(EmployeeRequestInsertDto employeeInsertDto);
    public List<Employee> getAllEmployee();

    public Employee getEmployeeById (Long id);

    public Employee checkLogin(LoginRequestDto loginRequestDto);
}
