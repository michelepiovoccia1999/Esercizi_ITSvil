package it.itsvil.employee2.util;

import it.itsvil.employee2.dto.response.EmployeeResponseDto;
import it.itsvil.employee2.entity.Employee;
import lombok.Data;

@Data
public class Convert {
    public EmployeeResponseDto convert (Employee employee){
        EmployeeResponseDto employeeDto= new EmployeeResponseDto();
        employeeDto.setN_idEmployee(employee.getN_idEmployee());
        employeeDto.setT_nomeEmployee(employee.getT_nomeEmployee());
        employeeDto.setT_cognomeEmployee(employee.getT_cognomeEmployee());
        employeeDto.setD_dataNascitaEmployee(employee.getD_dataNascita());
        employeeDto.setT_username(employee.getT_username());
        String passwordCifrata= Cifrapw.cifraPw(employee.getT_password());
        employeeDto.setT_password(passwordCifrata);
        return employeeDto;
    }
}
