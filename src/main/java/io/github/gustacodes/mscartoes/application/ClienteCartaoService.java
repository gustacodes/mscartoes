package io.github.gustacodes.mscartoes.application;

import io.github.gustacodes.mscartoes.domain.ClienteCartao;
import io.github.gustacodes.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository cartaoRepository;

    public List<ClienteCartao> listCartaoCpf(String cpf) {
        return cartaoRepository.findByCpf(cpf);
    }

}
