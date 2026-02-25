package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.exception.EmailJaCadastradoException;
import com.deliverytech.delivery_api.exception.ResourceNotFoundException;
import com.deliverytech.delivery_api.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ClienteService(repository, modelMapper);
    }

    // =========================
    // CADASTRAR CLIENTE
    // =========================

    @Test
    void deveCadastrarClienteComSucesso() {

        ClienteDTO dto = new ClienteDTO();
        dto.setNome("João");
        dto.setEmail("joao@email.com");

        when(repository.findAll()).thenReturn(List.of());
        when(repository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClienteResponseDTO response = service.cadastrarCliente(dto);

        assertEquals("João", response.getNome());
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoEmailDuplicado() {

        Cliente cliente = new Cliente();
        cliente.setEmail("joao@email.com");

        ClienteDTO dto = new ClienteDTO();
        dto.setEmail("joao@email.com");

        when(repository.findAll()).thenReturn(List.of(cliente));

        assertThrows(EmailJaCadastradoException.class,
                () -> service.cadastrarCliente(dto));
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    @Test
    void deveBuscarClientePorId() {

        Cliente cliente = new Cliente();
        cliente.setNome("João");

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = service.buscarClientePorId(1L);

        assertEquals("João", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrado() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.buscarClientePorId(1L));
    }

    // =========================
    // LISTAR ATIVOS
    // =========================

    @Test
    void deveListarApenasClientesAtivos() {

        Cliente ativo = new Cliente();
        ativo.setNome("Ativo");
        ativo.setAtivo(true);

        Cliente inativo = new Cliente();
        inativo.setNome("Inativo");
        inativo.setAtivo(false);

        when(repository.findAll()).thenReturn(List.of(ativo, inativo));

        List<ClienteResponseDTO> lista = service.listarClientesAtivos();

        assertEquals(1, lista.size());
        assertEquals("Ativo", lista.get(0).getNome());
    }

    // =========================
    // ATIVAR/DESATIVAR
    // =========================

    @Test
    void deveAlternarStatusCliente() {

        Cliente cliente = new Cliente();
        cliente.setAtivo(true);

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        service.ativarDesativarCliente(1L);

        assertFalse(cliente.isAtivo());
        verify(repository).save(cliente);
    }

    // =========================
    // ATUALIZAR CLIENTE
    // =========================

    @Test
    void deveAtualizarCliente() {

        Cliente cliente = new Cliente();
        cliente.setNome("Antigo");

        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Novo");

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO response = service.atualizarCliente(1L, dto);

        assertEquals("Novo", response.getNome());
        verify(repository).save(cliente);
    }
}