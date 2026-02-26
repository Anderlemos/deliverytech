package com.deliverytech.delivery_api.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.exception.EmailJaCadastradoException;
import com.deliverytech.delivery_api.exception.ResourceNotFoundException;
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

        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente.setAtivo(true);

        repository.save(cliente);

        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    /**
     * Buscar cliente por ID
     */
    public ClienteResponseDTO buscarClientePorId(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    /**
     * Listar clientes ativos com paginação
     */
    public Page<ClienteResponseDTO> listarClientesAtivos(Pageable pageable) {

        return repository.findByAtivoTrue(pageable)
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class));
    }

    /**
     * Ativar ou desativar cliente
     */
    @Transactional
    public void ativarDesativarCliente(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        cliente.setAtivo(!cliente.isAtivo());

        repository.save(cliente);
    }

    /**
     * Atualizar cliente
     */
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        // Validação de email duplicado (caso esteja alterando)
        if (!cliente.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        repository.save(cliente);

        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }
}