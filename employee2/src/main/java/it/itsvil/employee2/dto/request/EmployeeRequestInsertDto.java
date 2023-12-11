package it.itsvil.employee2.dto.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EmployeeRequestInsertDto {
    private LocalDate d_dataNascitaEmployee;
    private String t_cognomeEmployee;
    private String t_nomeEmployee;
    private String t_username;
    private String t_password;
}
