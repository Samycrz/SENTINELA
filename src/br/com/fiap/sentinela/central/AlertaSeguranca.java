package br.com.fiap.sentinela.central;

import br.com.fiap.sentinela.dispositivos.DispositivoMonitoramento;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

public class AlertaSeguranca {
    private DispositivoMonitoramento sensorOrigem;
    private ZonaRestrita zonaAfetada;
    private String severidade;
    private String mensagem;

    public AlertaSeguranca(DispositivoMonitoramento sensorOrigem, ZonaRestrita zonaAfetada, String severidade, String mensagem) {
        this.sensorOrigem = sensorOrigem;
        this.zonaAfetada = zonaAfetada;
        this.severidade = severidade;
        this.mensagem = mensagem;
    }

    public String getSeveridade() {
        return severidade;
    }

    @Override
    public String toString() {
        return String.format("[%s] ZONA: %s | ORIGEM: %s | DETALHE: %s",
                severidade,
                zonaAfetada.getIdentificador(),
                sensorOrigem.getCodigoIdentificador(),
                mensagem);
    }
}
