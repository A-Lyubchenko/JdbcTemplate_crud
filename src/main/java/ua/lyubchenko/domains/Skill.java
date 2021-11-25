package ua.lyubchenko.domains;

import lombok.*;


import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Skill {


    private int id;


    @NotNull(message = "Department cannot be null")
    private String department;


    @NotNull(message = "Level cannot be null")
    private String level;
}
