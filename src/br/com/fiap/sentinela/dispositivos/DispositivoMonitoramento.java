package br.com.fiap.sentinela.dispositivos;

import br.com.fiap.sentinela.central.AlertaSeguranca;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

public abstract class DispositivoMonitoramento {
    private String codigoIdentificador;
    private String localizacao;
    private boolean emOperacao;

    public DispositivoMonitoramento(String codigoIdentificador, String localizacao, boolean emOperacao) {
        if (codigoIdentificador == null || codigoIdentificador.trim().isEmpty()) {
            throw new IllegalArgumentException("Código identificador obrigatório para o dispositivo.");
        }
        this.codigoIdentificador = codigoIdentificador;
        this.localizacao = localizacao;
        this.emOperacao = emOperacao;
    }

    public abstract AlertaSeguranca analisarEstado(ZonaRestrita zonaMonitorada);

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public boolean isEmOperacao() {
        return emOperacao;
    }
}