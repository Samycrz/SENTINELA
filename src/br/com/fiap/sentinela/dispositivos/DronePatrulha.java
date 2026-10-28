package br.com.fiap.sentinela.dispositivos;

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
}