package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClienteDTO;
import com.deliverytech.delivery_api.dto.ClienteResponseDTO;
import com.deliverytech.delivery_api.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // CADASTRAR CLIENTE
    // =========================
    @Test
    void deveCadastrarCliente() throws Exception {

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(1L);

        when(service.cadastrarCliente(any())).thenReturn(response);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ClienteDTO())))
                .andExpect(status().isCreated());
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    @Test
    void deveBuscarClientePorId() throws Exception {

        when(service.buscarClientePorId(1L))
                .thenReturn(new ClienteResponseDTO());

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk());
    }

    // =========================
    // LISTAR CLIENTES
    // =========================
    @Test
    void deveListarClientesAtivos() throws Exception {

        when(service.listarClientesAtivos(any()))
                .thenReturn(new PageImpl<>(
                        List.of(new ClienteResponseDTO()),
                        PageRequest.of(0, 10),
                        1
                ));

        mockMvc.perform(get("/api/clientes?page=0&size=10"))
                .andExpect(status().isOk());
    }

    // =========================
    // ATIVAR / DESATIVAR
    // =========================
    @Test
    void deveAtivarOuDesativarCliente() throws Exception {

        doNothing().when(service).ativarDesativarCliente(1L);

        mockMvc.perform(patch("/api/clientes/1"))
                .andExpect(status().isNoContent());
    }

    // =========================
    // ATUALIZAR
    // =========================
    @Test
    void deveAtualizarCliente() throws Exception {

        when(service.atualizarCliente(eq(1L), any()))
                .thenReturn(new ClienteResponseDTO());

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ClienteDTO())))
                .andExpect(status().isOk());
    }
}