package br.com.fiap.sentinela.dispositivos;

import br.com.fiap.sentinela.central.AlertaSeguranca;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

public class SensorPerimetral extends DispositivoMonitoramento {
    private String setorVigiado;
    private boolean violacaoDetectada;

    public SensorPerimetral(String codigoIdentificador, String localizacao, boolean emOperacao, String setorVigiado) {
        super(codigoIdentificador, localizacao, emOperacao);
        this.setorVigiado = setorVigiado;
        this.violacaoDetectada = false;
    }

    public void detectarViolacao() {
        this.violacaoDetectada = true;
    }

    public void rearmarSensor() {
        this.violacaoDetectada = false;
    }

    public String getSetorVigiado() {
        return setorVigiado;
    }

    public boolean isViolacaoDetectada() {
        return violacaoDetectada;
    }

    @Override
    public AlertaSeguranca analisarEstado(ZonaRestrita zonaMonitorada) {
        if (this.violacaoDetectada) {
            return new AlertaSeguranca(this, zonaMonitorada, "ALTO",
                    "Violação física detectada no setor: " + this.setorVigiado);
        }
        return null;
    }
}
