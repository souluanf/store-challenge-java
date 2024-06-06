package dev.luanfernandes.store.service;

public interface VendaService {
    void comprarProduto(int id, int quantidade);

    void finalizarCompraCarrinho();
}
