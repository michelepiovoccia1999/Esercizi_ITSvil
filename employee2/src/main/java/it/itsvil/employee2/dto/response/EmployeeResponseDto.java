package it.itsvil.employee2.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long n_idEmployee;
    private LocalDate d_dataNascitaEmployee;
    private String t_cognomeEmployee;
    private String t_nomeEmployee;
    private String t_username;
    private String t_password;
}
