package br.com.fiap.sentinela.dispositivos;

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