package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Map;

public class Pedido {
    public enum Status { PAGO }

    private Map<String, Integer> itensPorCodigo;
    private BigDecimal totalPago;
    private String codigoAutorizacao;
    private Status status;

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago, String codigoAutorizacao, Status status) {
        this.itensPorCodigo = itensPorCodigo;
        this.totalPago = totalPago;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public Status getStatus() {
        return status;
    }

    public int getQuantidadeItem(String codigo) {
        if (itensPorCodigo.containsKey(codigo)) {
            return itensPorCodigo.get(codigo);
        }
        return 0;
    }
}
