package pe.upc.evaluaciondd.business;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import pe.upc.evaluaciondd.dtos.PlayerDTO;
import pe.upc.evaluaciondd.entity.Player;
import pe.upc.evaluaciondd.repositories.RepositoryPlayer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessPlayer {
    @Autowired
    private RepositoryPlayer repositoryPlayer;

    public Player register (Player player )
    {
        Player postulant1 = repositoryPlayer.save(player);
        return postulant1;
    }

    public List<Player>  obtenerReporte()
    {
        return  repositoryPlayer.findAll();
    }

    public  Player  actualizarDatos(Long id, Player player)
    {
        Player postulantAntiguo = repositoryPlayer.findById(id).get();
        player.setId(id);
        return  repositoryPlayer.save(player);
    }
    public Player borrar(Long codigo) throws  Exception {
        Player player = repositoryPlayer.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        repositoryPlayer.delete(player);
        return player;
    }





    private PlayerDTO convertToDto(Player player) {
        ModelMapper modelMapper = new ModelMapper();
        PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);
        return playerDTO;
    }

    private Player convertToEntity(PlayerDTO playerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Player post = modelMapper.map(playerDTO, Player.class);
        return post;
    }
    private List<PlayerDTO> convertToLisDto(List<Player> list) {
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
