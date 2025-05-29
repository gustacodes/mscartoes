package io.github.gustacodes.mscartoes.application;

import io.github.gustacodes.mscartoes.application.representation.CartaoSaveRequest;
import io.github.gustacodes.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartoes")
public class CartoesResource {

    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao model = request.toModel();
        cartaoService.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }
}
