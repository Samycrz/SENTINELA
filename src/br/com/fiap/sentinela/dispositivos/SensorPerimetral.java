package br.com.fiap.sentinela.dispositivos;

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
}
