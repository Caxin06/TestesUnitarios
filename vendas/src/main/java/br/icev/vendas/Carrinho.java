package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa");
        }

        if (itens.containsKey(produto)) {
            int quantidadeAtual = itens.get(produto);
            int novaQuantidade = quantidadeAtual + quantidade;
            itens.put(produto, novaQuantidade);
        } else {
            itens.put(produto, quantidade);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (Produto produto : itens.keySet()) {
            int quantidade = itens.get(produto);
            BigDecimal preco = produto.getPrecoUnitario();


            BigDecimal valorItem = preco.multiply(BigDecimal.valueOf(quantidade));
            total = total.add(valorItem);
        }

        return UtilDinheiro.arredondar2(total);
    }

    public int getTotalItens() {
        int totalDeItens = 0;
        for (Integer qtd : itens.values()) {
            totalDeItens += qtd;
        }
        return totalDeItens;
    }

    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        BigDecimal subtotal = getSubtotal();
        BigDecimal totalComDesconto = politica.aplicar(subtotal);

        if (totalComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return new BigDecimal("0.00");
        }

        return UtilDinheiro.arredondar2(totalComDesconto);
    }
}