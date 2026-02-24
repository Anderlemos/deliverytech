package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.exception.EmailJaCadastradoException;
import com.deliverytech.delivery_api.repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ModelMapper modelMapper;

    /**
     * Cadastrar cliente com validação de email único.
     */
    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteDTO dto) {

        // Regra de negócio: email não pode ser duplicado
        if (repository.findAll()
                .stream()
                .anyMatch(c -> c.getEmail().equals(dto.getEmail()))) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        // Converter DTO para entidade
        Cliente cliente = modelMapper.map(dto, Cliente.class);

        cliente.setAtivo(true);

        repository.save(cliente);

        // Retornar DTO de resposta
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    /**
     * Buscar cliente por ID
     */
    public ClienteResponseDTO buscarClientePorId(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    /**
     * Listar apenas clientes ativos
     */
    public List<ClienteResponseDTO> listarClientesAtivos() {

        return repository.findAll()
                .stream()
                .filter(Cliente::isAtivo)
                .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Ativar ou desativar cliente
     */
    @Transactional
    public void ativarDesativarCliente(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setAtivo(!cliente.isAtivo());

        repository.save(cliente);
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        repository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }
}