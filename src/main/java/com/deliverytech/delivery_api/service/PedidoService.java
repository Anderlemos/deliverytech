package com.deliverytech.delivery_api.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.ClienteRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Pedido criar(Long clienteId, Pedido pedido) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (!cliente.isAtivo()) {
            throw new RuntimeException("Cliente inativo não pode fazer pedidos");
        }

        pedido.setCliente(cliente);

        if (pedido.getValorTotal() == null || pedido.getValorTotal() <= 0) {
            throw new RuntimeException("Valor do pedido inválido");
        }

        pedido.setStatus("CRIADO");

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido atualizarStatus(Long pedidoId, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);

        return pedidoRepository.save(pedido);
    }
}