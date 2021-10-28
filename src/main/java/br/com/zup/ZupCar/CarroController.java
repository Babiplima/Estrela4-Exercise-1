package br.com.zup.ZupCar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros") // Mapea as requisições para o endpoint nele contido
public class CarroController {


    private List<CarroDTO> concessionaria = new ArrayList<>();

    @GetMapping // é o Request Mapping utilizando o Verbo GET do PROTOCOLO HTTP
    public List<CarroDTO> exibirTodosOsCarros() {
        return concessionaria;
    }

    @PostMapping // é o Request Mapping utilizando o Verbo POST do PROTOCOLO HTTP
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        // Todo Classe DTO são representações de Json seja de Entrada ou Saida.
        concessionaria.add(carroDTO);
    }

    @GetMapping("/nomeDoCarro")
    public CarroDTO exibirCarro(@PathVariable String nomeDoCarro) {
        for (CarroDTO objeto : concessionaria) {
            if (objeto.getModelo().equals(nomeDoCarro)) {
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/nomeDoCarro")
    public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carroDTO) {
        CarroDTO carrobjeto = null;
        for (CarroDTO objeto : concessionaria) {
            if (objeto.getModelo().equals(nomeDoCarro)) {
                carrobjeto = objeto;
            }

        }
        if (carrobjeto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
        }

        carrobjeto.setAno(carroDTO.getAno());
        carrobjeto.setCor(carroDTO.getCor());
        carrobjeto.setMotor(carroDTO.getMotor());
        return carrobjeto;

    }

}



