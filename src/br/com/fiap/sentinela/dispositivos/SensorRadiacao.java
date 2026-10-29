package br.com.fiap.sentinela.dispositivos;

import br.com.fiap.sentinela.central.AlertaSeguranca;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

public class SensorRadiacao extends DispositivoMonitoramento {
    private double leituraAtual;
    private double limiteSeguranca;

    public SensorRadiacao(String codigoIdentificador, String localizacao, boolean emOperacao, double limiteSeguranca) {
        super(codigoIdentificador, localizacao, emOperacao);
        this.limiteSeguranca = limiteSeguranca;
        this.leituraAtual = 0.0;
    }

    public void atualizarLeitura(double leituraRegistrada) {
        this.leituraAtual = leituraRegistrada;
    }

    public double getLeituraAtual() {
        return leituraAtual;
    }

    public double getLimiteSeguranca() {
        return limiteSeguranca;
    }

    @Override
    public AlertaSeguranca analisarEstado(ZonaRestrita zonaMonitorada) {
        zonaMonitorada.registrarLeituraRadiacao(this.leituraAtual);
        String riscoZona = zonaMonitorada.classificarRisco();

        if ("CRITICO".equals(riscoZona)) {
            return new AlertaSeguranca(this, zonaMonitorada, "CRITICO",
                    "Nível de radiação (" + this.leituraAtual + " µSv/h) excedeu o limite seguro.");
        } else if ("ATENCAO".equals(riscoZona)) {
            return new AlertaSeguranca(this, zonaMonitorada, "ATENCAO",
                    "Nível de radiação (" + this.leituraAtual + " µSv/h) entrou em estado de alerta próximo ao limite.");
        }

        return null;
    }
}