package io.github.gustacodes.mscartoes.application;

import io.github.gustacodes.mscartoes.application.representation.CartaoSaveRequest;
import io.github.gustacodes.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.gustacodes.mscartoes.domain.Cartao;
import io.github.gustacodes.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartoes")
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao model = request.toModel();
        cartaoService.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam String cpf) {
        List<ClienteCartao> clienteCartaos = clienteCartaoService.listCartaoCpf(cpf);
        List<CartoesPorClienteResponse> resultList = clienteCartaos.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }
}
