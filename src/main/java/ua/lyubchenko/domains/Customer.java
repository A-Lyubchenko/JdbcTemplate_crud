package ua.lyubchenko.domains;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private int id;


    @NotEmpty(message = "Name cannot be null") // Текст, который будет отображаться при неправильно введенных данных
    private String name;


    @NotEmpty(message = "Location cannot be null")
    private String location;
}
