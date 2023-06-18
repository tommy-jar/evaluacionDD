package pe.upc.evaluaciondd.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.upc.evaluaciondd.business.BusinessPlayer;
import pe.upc.evaluaciondd.dtos.PlayerDTO;
import pe.upc.evaluaciondd.entity.Player;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ControllerPlayer {
    @Autowired
    private BusinessPlayer businessPlayer;

    Logger logger = LoggerFactory.getLogger(ControllerPlayer.class);

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>>  obtenerAutores(){
        List<Player> list = businessPlayer.obtenerReporte();
        List<PlayerDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<PlayerDTO>>(listDto,HttpStatus.OK);
    }

    @PostMapping("/player")
    public PlayerDTO register(@RequestBody PlayerDTO playerDTO)
    {
        Player player;
        try {
            player = convertToEntity(playerDTO);
            player = businessPlayer.register(player);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO hay");
        }
        return convertToDto(player);
    }


    @PutMapping("/player/{id}")
    public ResponseEntity<PlayerDTO> actualizar(@PathVariable(value = "id") Long id,
                                                   @RequestBody PlayerDTO playerDTO)
    {
        Player player;
        Player playerActualizado;
        try {
            player = convertToEntity(playerDTO);
            playerActualizado = businessPlayer.actualizarDatos(id,player);
            playerDTO = convertToDto(playerActualizado);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return new ResponseEntity<PlayerDTO>(playerDTO, HttpStatus.OK);
    }


    @DeleteMapping("/player/{codigo}")
    public  ResponseEntity<PlayerDTO> borrarPlayer(@PathVariable(value = "codigo") Long codigo){
        Player player;
        PlayerDTO playerDTO;
        try {
            player = businessPlayer.borrar(codigo);
            logger.debug("Postulante Eliminado");
            playerDTO = convertToDto(player);
            return new ResponseEntity<PlayerDTO>(playerDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de eliminación", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar, sorry");
        }
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
