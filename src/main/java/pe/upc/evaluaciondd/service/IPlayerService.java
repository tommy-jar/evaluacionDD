package pe.upc.evaluaciondd.service;

import pe.upc.evaluaciondd.entity.Player;

import java.util.List;

public interface IPlayerService {

    public void insertar(Player author);

    public void eliminar(int idPlayer);

    public Player listarId(int idPlayer);

    List<Player> listar();
}
