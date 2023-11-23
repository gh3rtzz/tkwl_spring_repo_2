package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeAddressDTO {

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @NotEmpty
    private String addressLine1;

    private String addressLine2;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private Integer fkEmployee;

}
