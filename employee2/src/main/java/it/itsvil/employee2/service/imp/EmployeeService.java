package it.itsvil.employee2.service.imp;
import it.itsvil.employee2.dto.request.EmployeeRequestInsertDto;
import it.itsvil.employee2.dto.request.LoginRequestDto;
import it.itsvil.employee2.entity.Employee;
import it.itsvil.employee2.repository.EmployeeDao;
import it.itsvil.employee2.service.in.EmployeeServiceIn;
import it.itsvil.employee2.util.Cifrapw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceIn {
    private EmployeeDao employeeDao;
    @Autowired
    public EmployeeService (EmployeeDao employeeDao){
        this.employeeDao=employeeDao;
    }
    @Override
    public Employee addEmployee(EmployeeRequestInsertDto employeeInsertDto) {
        Employee employee= new Employee();
        if (employeeInsertDto!=null){
            employee.setT_nomeEmployee(employeeInsertDto.getT_nomeEmployee());
            employee.setT_cognomeEmployee(employeeInsertDto.getT_cognomeEmployee());
            employee.setD_dataNascita(employeeInsertDto.getD_dataNascitaEmployee());
            employee.setT_username(employeeInsertDto.getT_username());
            String passwordCifrata= Cifrapw.cifraPw(employeeInsertDto.getT_password());
            employee.setT_password(passwordCifrata);
            return employeeDao.save(employee);
        }
        return employee;
    }
    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee= new Employee();
        employee.setN_idEmployee(-1L);
        return  employeeDao.findById(id).orElseThrow();
    }

    @Override
    public Employee checkLogin(LoginRequestDto loginRequestDto) {
        List<Employee> employees = new ArrayList<>();
        employees= employeeDao.findAll();
        if (loginRequestDto!=null) {
            for (Employee e : employees) {
                if (e.getT_password().equals(loginRequestDto.getT_password()) && e.getT_username().equals(loginRequestDto.getT_username()))
                    return e;
            }
        }
        return null;
    }
}
