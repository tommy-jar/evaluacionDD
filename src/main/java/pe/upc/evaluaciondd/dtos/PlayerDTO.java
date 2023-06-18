package pe.upc.evaluaciondd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name;
    private String lastname;
    private Integer age;
    private double height;
    private String position;

}
