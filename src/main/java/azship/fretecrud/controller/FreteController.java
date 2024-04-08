package azship.fretecrud.controller;

import azship.fretecrud.model.Frete;
import azship.fretecrud.model.FreteInsertDTO;
import azship.fretecrud.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/frete")
public class FreteController {

    @Autowired
    FreteService freteService;

    @GetMapping("/search/{page}")
    public ResponseEntity<Page<Frete>> searchAllFrete(@PathVariable int page) {
        return new ResponseEntity<>(freteService.searchAllFrete(page), HttpStatus.OK);
    }
    @GetMapping("/search/{param}/{page}")
    public ResponseEntity<Page<Frete>> searchFrete(@PathVariable String param,
                                                   @PathVariable Integer page) {
        return new ResponseEntity<>(freteService.searchFrete(param, page), HttpStatus.OK);
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertFrete(@RequestBody HashMap<String, String> freteInsert) {
        try {
            Frete frete = new Frete();
            frete.fillCustomPropertiesFromHashMap(freteInsert);
            freteService.insertFrete(frete);

            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFrete(@RequestBody String id) {
        try {
            freteService.deleteFrete(id);
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFrete(@RequestBody FreteInsertDTO dto) {
        try {
            freteService.updateFrete(dto);
            return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/populate")
    public ResponseEntity<String> populateDb() {
        for (int i = 1; i < 31; i++) {
            Frete frete = new Frete();

            HashMap<String, String> valores = new HashMap<>();

            valores.put("Constante", "teste");

            if (i % 2 == 0) {
                valores.put("cubagem", String.valueOf(10*i));
                valores.put("peso", String.valueOf(i));
                valores.put("distancia percorrida", String.valueOf(5*i));
            } else {
                valores.put("tempo de entrega", String.valueOf(24*i));
                valores.put("peso", String.valueOf(i));
            }
            frete.fillCustomPropertiesFromHashMap(valores);
            freteService.insertFrete(frete);
        }

        return new ResponseEntity<>("Valores adicionados", HttpStatus.OK);
    }

}
