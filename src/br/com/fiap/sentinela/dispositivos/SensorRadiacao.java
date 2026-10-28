package br.com.fiap.sentinela.dispositivos;

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
}