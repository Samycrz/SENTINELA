package br.com.fiap.sentinela.central;

import br.com.fiap.sentinela.dispositivos.DispositivoMonitoramento;
import br.com.fiap.sentinela.dispositivos.DronePatrulha;
import br.com.fiap.sentinela.dispositivos.SensorPerimetral;
import br.com.fiap.sentinela.dispositivos.SensorRadiacao;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CentralMonitoramento {

    private Map<DispositivoMonitoramento, ZonaRestrita> mapeamentoDispositivos;

    public CentralMonitoramento() {
        this.mapeamentoDispositivos = new LinkedHashMap<>();
    }

    public void registrarDispositivo(DispositivoMonitoramento dispositivo, ZonaRestrita zonaMonitorada) {
        this.mapeamentoDispositivos.put(dispositivo, zonaMonitorada);
    }

    public List<AlertaSeguranca> analisarLeituras() {
        List<AlertaSeguranca> alertasGerados = new ArrayList<>();

        for (Map.Entry<DispositivoMonitoramento, ZonaRestrita> entrada : mapeamentoDispositivos.entrySet()) {
            DispositivoMonitoramento dispositivoAtual = entrada.getKey();
            ZonaRestrita zonaAtual = entrada.getValue();

            if (!dispositivoAtual.isEmOperacao()) {
                continue;
            }

            if (dispositivoAtual instanceof SensorRadiacao) {
                SensorRadiacao sensorRad = (SensorRadiacao) dispositivoAtual;

                zonaAtual.registrarLeituraRadiacao(sensorRad.getLeituraAtual());
                String riscoZona = zonaAtual.classificarRisco();

                if ("CRITICO".equals(riscoZona)) {
                    alertasGerados.add(new AlertaSeguranca(sensorRad, zonaAtual, "CRITICO", "Nível de radiação (" + sensorRad.getLeituraAtual() + " µSv/h) excedeu o limite seguro."));
                } else if ("ATENCAO".equals(riscoZona)) {
                    alertasGerados.add(new AlertaSeguranca(sensorRad, zonaAtual, "ATENCAO", "Nível de radiação atingiu o limite exato de segurança."));
                }

            } else if (dispositivoAtual instanceof SensorPerimetral) {
                SensorPerimetral sensorPer = (SensorPerimetral) dispositivoAtual;

                if (sensorPer.isViolacaoDetectada()) {
                    alertasGerados.add(new AlertaSeguranca(sensorPer, zonaAtual, "ALTO", "Violação física detectada no setor: " + sensorPer.getSetorVigiado()));
                }

            } else if (dispositivoAtual instanceof DronePatrulha) {
                DronePatrulha drone = (DronePatrulha) dispositivoAtual;

                if (drone.isAmeacaConfirmadaVisualmente()) {
                    alertasGerados.add(new AlertaSeguranca(drone, zonaAtual, "CRITICO", "Veículo/Intruso não identificado confirmado via visão computacional."));
                }
            }
        }
        return alertasGerados;
    }
}