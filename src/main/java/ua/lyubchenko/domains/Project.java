package ua.lyubchenko.domains;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    private int id;


     @NotNull(message = "Name cannot be null")
    private String name;


     @Size(min = 10, max = 10, message
            = "About start - (example: 2021-03-07) must be 10 decimal")
     @NotNull(message = "Start cannot be null")
    private String start;


     @NotNull(message = "Coast cannot be null")
     @Digits(integer = 10, fraction = 0, message = "Coast must be less than 10 decimal")
     @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer coast;
}

