package pe.upc.evaluaciondd.service;



import pe.upc.evaluaciondd.entity.Role;

import java.util.List;



public interface IRoleService {
    public void insert(Role role);

    List<Role> list();

}
