package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.exception.EmailJaCadastradoException;
import com.deliverytech.delivery_api.exception.ResourceNotFoundException;
import com.deliverytech.delivery_api.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private ClienteResponseDTO clienteResponseDTO;

    @BeforeEach
    void setup() {

        cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@email.com");
        cliente.setAtivo(true);

        clienteDTO = new ClienteDTO();
        clienteDTO.setNome("João");
        clienteDTO.setEmail("joao@email.com");

        clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(1L);
        clienteResponseDTO.setNome("João");
        clienteResponseDTO.setEmail("joao@email.com");
        clienteResponseDTO.setAtivo(true);
    }

    @Test
    void deveCadastrarCliente() {

        when(repository.existsByEmail(clienteDTO.getEmail())).thenReturn(false);
        when(modelMapper.map(clienteDTO, Cliente.class)).thenReturn(cliente);
        when(repository.save(any())).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteResponseDTO.class))
                .thenReturn(clienteResponseDTO);

        ClienteResponseDTO response = service.cadastrarCliente(clienteDTO);

        assertNotNull(response);
        assertEquals("João", response.getNome());
        verify(repository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {

        when(repository.existsByEmail(clienteDTO.getEmail())).thenReturn(true);

        assertThrows(EmailJaCadastradoException.class,
                () -> service.cadastrarCliente(clienteDTO));

        verify(repository, never()).save(any());
    }

    @Test
    void deveBuscarClientePorId() {

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(modelMapper.map(cliente, ClienteResponseDTO.class))
                .thenReturn(clienteResponseDTO);

        ClienteResponseDTO response = service.buscarClientePorId(1L);

        assertNotNull(response);
        assertEquals("João", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.buscarClientePorId(1L));
    }

    @Test
    void deveListarClientesAtivos() {

        Page<Cliente> page = new PageImpl<>(List.of(cliente));

        when(repository.findByAtivoTrue(any())).thenReturn(page);
        when(modelMapper.map(cliente, ClienteResponseDTO.class))
                .thenReturn(clienteResponseDTO);

        Page<ClienteResponseDTO> resultado =
                service.listarClientesAtivos(PageRequest.of(0, 10));

        assertEquals(1, resultado.getTotalElements());
    }

    @Test
    void deveAtivarOuDesativarCliente() {

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        service.ativarDesativarCliente(1L);

        verify(repository).save(cliente);
    }

    @Test
    void deveAtualizarCliente() {

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(modelMapper.map(cliente, ClienteResponseDTO.class))
                .thenReturn(clienteResponseDTO);

        ClienteResponseDTO response =
                service.atualizarCliente(1L, clienteDTO);

        assertNotNull(response);
        verify(repository).save(any());
    }
}