package br.com.fiap.sentinela.dispositivos;

import br.com.fiap.sentinela.central.AlertaSeguranca;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

public class DronePatrulha extends DispositivoMonitoramento {
    private String rotaPatrulha;
    private int bateriaPercentual;
    private boolean ameacaConfirmadaVisualmente;

    public DronePatrulha(String codigoIdentificador, String localizacao, boolean emOperacao, String rotaPatrulha, int bateriaPercentual) {
        super(codigoIdentificador, localizacao, emOperacao);
        this.rotaPatrulha = rotaPatrulha;
        this.bateriaPercentual = bateriaPercentual;
        this.ameacaConfirmadaVisualmente = false;
    }

    public void acionarCameraVisaoComputacional() {
        this.ameacaConfirmadaVisualmente = true;
    }

    public void resetarCamera() {
        this.ameacaConfirmadaVisualmente = false;
    }

    public String getRotaPatrulha() {
        return rotaPatrulha;
    }

    public int getBateriaPercentual() {
        return bateriaPercentual;
    }

    public boolean isAmeacaConfirmadaVisualmente() {
        return ameacaConfirmadaVisualmente;
    }

    @Override
    public AlertaSeguranca analisarEstado(ZonaRestrita zonaMonitorada) {
        if (this.ameacaConfirmadaVisualmente) {
            return new AlertaSeguranca(this, zonaMonitorada, "CRITICO",
                    "Veículo/Intruso não identificado confirmado via visão computacional.");
        }
        return null;
    }
}