package pe.upc.evaluaciondd.dtos;

import pe.upc.evaluaciondd.entity.Role;

import java.util.List;

public class UserDTO {
    private Long id;

    private String username;

    private String password;
    private Boolean enabled;

    private List<Role> roles;
}
