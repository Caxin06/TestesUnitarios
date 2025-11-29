package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;
import java.util.HashMap;
import java.util.Map;

public class Estoque {

    private Map<String, Integer> itens = new HashMap<>();

    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {

        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade tem que ser maior que zero");
        }

        if (itens.containsKey(codigo)) {
            int atual = itens.get(codigo);
            itens.put(codigo, atual + quantidade);
        } else {
            itens.put(codigo, quantidade);
        }
    }

    public int getDisponivel(String codigo) {
        if (itens.containsKey(codigo)) {
            return itens.get(codigo);
        }
        return 0;
    }

    public void reservar(String codigo, int quantidade)
            throws SemEstoqueException, QuantidadeInvalidaException {

        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade inválida para reserva");
        }

        int disponivel = getDisponivel(codigo);

        if (disponivel < quantidade) {
            throw new SemEstoqueException("Estoque insuficiente para o produto " + codigo);
        }

        itens.put(codigo, disponivel - quantidade);
    }
}
