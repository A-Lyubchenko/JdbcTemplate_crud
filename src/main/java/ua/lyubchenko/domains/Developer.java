package ua.lyubchenko.domains;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Developer {

    private int id;


    @NotNull(message = "Name cannot be null")
    private String name;


    @Min(value = 18, message = "Age should not be less than 18")
     @Max(value = 55, message = "Age should not be greater than 55")
    @NotNull(message = "Age cannot be null")
    private int age;


     @Size(min = 4, max = 6, message
     = "About sex must be between 4 - (male) or 6 - (female) characters")
     @NotNull(message = "Sex cannot be null")
    private String sex;


     @Size(min = 10, max = 10, message
       = "About phone_number must be 10 decimal")
     @NotNull(message = "Phone_number cannot be null")
    private String phone_number;

     @NotNull(message = "Salary cannot be null")
     @Digits(integer = 5, fraction = 0, message = "Coast must be less than 5 decimal")
     @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer salary;
}