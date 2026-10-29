package br.com.fiap.sentinela.central;

import br.com.fiap.sentinela.dispositivos.DispositivoMonitoramento;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

import java.util.ArrayList;
import java.util.List;

public class CentralMonitoramento {

    private class AlocacaoMonitoramento {
        private DispositivoMonitoramento dispositivoAlocado;
        private ZonaRestrita zonaMonitorada;

        public AlocacaoMonitoramento(DispositivoMonitoramento dispositivoAlocado, ZonaRestrita zonaMonitorada) {
            this.dispositivoAlocado = dispositivoAlocado;
            this.zonaMonitorada = zonaMonitorada;
        }

        public DispositivoMonitoramento getDispositivoAlocado() { return dispositivoAlocado; }
        public ZonaRestrita getZonaMonitorada() { return zonaMonitorada; }
    }

    private List<AlocacaoMonitoramento> alocacoesDispositivos;

    public CentralMonitoramento() {
        this.alocacoesDispositivos = new ArrayList<>();
    }

    public void registrarDispositivo(DispositivoMonitoramento dispositivo, ZonaRestrita zonaMonitorada) {
        this.alocacoesDispositivos.add(new AlocacaoMonitoramento(dispositivo, zonaMonitorada));
    }

    public List<AlertaSeguranca> analisarLeituras() {
        List<AlertaSeguranca> alertasGerados = new ArrayList<>();

        for (AlocacaoMonitoramento alocacao : this.alocacoesDispositivos) {
            DispositivoMonitoramento dispositivoAtual = alocacao.getDispositivoAlocado();
            ZonaRestrita zonaAtual = alocacao.getZonaMonitorada();

            if (!dispositivoAtual.isEmOperacao()) {
                continue;
            }

            // A MÁGICA DO POLIMORFISMO ACONTECE AQUI!
            // Sem instanceof. O dispositivo avalia a si mesmo baseado na sua própria classe filha.
            AlertaSeguranca alerta = dispositivoAtual.analisarEstado(zonaAtual);

            if (alerta != null) {
                alertasGerados.add(alerta);
            }
        }

        return alertasGerados;
    }
}