package br.com.fiap.sentinela.dominio;

public class ZonaRestrita {
    private String identificador;
    private String descricao;
    private double nivelRadiacaoAtual;
    private double limiteRadiacao;

    public ZonaRestrita(String identificador, String descricao, double limiteRadiacao) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador da zona restrita não pode ser vazio.");
        }
        this.identificador = identificador;
        this.descricao = descricao;
        this.limiteRadiacao = limiteRadiacao;
        this.nivelRadiacaoAtual = 0.0;
    }

    public void registrarLeituraRadiacao(double leitura) {
        if (leitura < 0.0) {
            throw new IllegalArgumentException("Falha crítica de sensor: Leitura abaixo de 0.0 µSv/h identificada.");
        }
        if (leitura > 1_000_000.0) {
            throw new IllegalArgumentException("Falha crítica de sensor: Leitura acima de 1.000.000 µSv/h identificada.");
        }
        this.nivelRadiacaoAtual = leitura;
    }

    public String classificarRisco() {
        if (this.nivelRadiacaoAtual < this.limiteRadiacao) {
            return "NORMAL";
        } else if (this.nivelRadiacaoAtual == this.limiteRadiacao) {
            return "ATENCAO";
        } else {
            return "CRITICO";
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNivelRadiacaoAtual() {
        return nivelRadiacaoAtual;
    }
}
