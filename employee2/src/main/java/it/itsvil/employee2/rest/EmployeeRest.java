package it.itsvil.employee2.rest;
import io.swagger.v3.oas.annotations.Operation;
import it.itsvil.employee2.dto.common.ResultDto;
import it.itsvil.employee2.dto.request.EmployeeRequestInsertDto;
import it.itsvil.employee2.dto.response.EmployeeResponseDto;
import it.itsvil.employee2.dto.response.LoginResponseDto;
import it.itsvil.employee2.entity.Employee;
import it.itsvil.employee2.service.imp.EmployeeService;
import it.itsvil.employee2.util.Cifrapw;
import it.itsvil.employee2.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value ="/employee")
public class EmployeeRest {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRest(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PutMapping(value= "/insertEmployee")
    @Operation (description = "Add new Employee")
    public ResponseEntity<ResultDto<EmployeeResponseDto>> addEmployee (@RequestBody EmployeeRequestInsertDto requestDto){
        ResultDto<EmployeeResponseDto> result = new ResultDto<>();
        try{
            Employee employee=employeeService.addEmployee(requestDto);
            if (employee!=null){
                if (employee.getN_idEmployee().equals(-1L)){
                    result.setSuccessFalseResponse("Employee not created");
                    result.setCode(HttpStatus.BAD_REQUEST.value());
                }else {
                    Convert convert = new Convert();
                    result.setSuccessTrueResponse("Employee created!");
                    result.setData(convert.convert(employee));
                }
            } else {
                result.setSuccessFalseResponse("Bad Request");
                result.setCode(HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            result.setFailureResponse("Server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllEmployee")
    @Operation (description = "Get list of Employee")
    public ResponseEntity<ResultDto<List<EmployeeResponseDto>>> getAllEmployee (){
        ResultDto<List<EmployeeResponseDto>> result = new ResultDto<>();
        try {
            List<EmployeeResponseDto> responseDto = new ArrayList<>();
            List<Employee> employees = employeeService.getAllEmployee();
            Convert convert = new Convert();
            for (Employee e : employees) {
                responseDto.add(convert.convert(e));
            }
            if (responseDto.isEmpty()){
                result.setSuccessFalseResponse("No Employee here");
                result.setCode(HttpStatus.BAD_REQUEST.value());
            } else {
                result.setSuccessTrueResponse("Employees number: " + responseDto.size());
                result.setData(responseDto);
            }
        } catch (Exception e) {
            result.setFailureResponse("Server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/getById")
    @Operation (description = "Get Employee by ID")
    public ResponseEntity<ResultDto<EmployeeResponseDto>> getEmployeeById (@RequestParam("n_id") Long id){
        ResultDto<EmployeeResponseDto> resultDto = new ResultDto<>();
        Employee employee= new Employee();
        try {
            employee = employeeService.getEmployeeById(id);
            if (employee!=null){
                Convert convert = new Convert();
                resultDto.setData(convert.convert(employee));
                resultDto.setSuccessTrueResponse("");
            } else {
                resultDto.setSuccessFalseResponse("Employee with id " + id + " not exist");
                resultDto.setCode(HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            resultDto.setFailureResponse("Server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }

    @GetMapping (value = "/login")
    public ResponseEntity<ResultDto<LoginResponseDto>> loginCheck (@RequestParam("t_username") String username,
                                                                   @RequestParam("t_password") String password ){
        ResultDto<LoginResponseDto> resultDto = new ResultDto<>();
        LoginResponseDto responseDto = new LoginResponseDto();
        List<Employee> employees= new ArrayList<>();
        try {
            employees = employeeService.getAllEmployee();
            for (Employee employee : employees) {
                if (employee.getT_username().equals(username) && employee.getT_password().equals(password)) {
                    responseDto.setT_username(employee.getT_username());
                    String passwordCifrata = Cifrapw.cifraPw(password);
                    responseDto.setT_password(passwordCifrata);
                    resultDto.setData(responseDto);
                    resultDto.setSuccessTrueResponse("Login effettuato con successo!");
                }
            }
            resultDto.setSuccessFalseResponse("No employee with these credentials!");
            resultDto.setCode(HttpStatus.BAD_REQUEST.value());

        }catch (Exception e) {
            resultDto.setFailureResponse("Server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultDto,HttpStatus.OK);
    }
}