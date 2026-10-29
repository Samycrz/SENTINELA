package br.com.fiap.sentinela;

import br.com.fiap.sentinela.central.AlertaSeguranca;
import br.com.fiap.sentinela.central.CentralMonitoramento;
import br.com.fiap.sentinela.dispositivos.DronePatrulha;
import br.com.fiap.sentinela.dispositivos.SensorPerimetral;
import br.com.fiap.sentinela.dispositivos.SensorRadiacao;
import br.com.fiap.sentinela.dominio.ZonaRestrita;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("     INICIALIZANDO SISTEMA SENTINELA (V1.0)      ");

        ZonaRestrita casaReator = new ZonaRestrita("ZNR-01", "Casa do Reator Principal", 50.0);
        ZonaRestrita patioArmazenamento = new ZonaRestrita("ZNR-02", "Pátio de Armazenamento de Resíduos", 15.0);

        SensorRadiacao sensorCasaReator = new SensorRadiacao("SRAD-100", "Interior do Reator", true, 50.0);
        SensorPerimetral sensorPatio = new SensorPerimetral("SPER-200", "Cerca Norte", true, "Setor Norte - Externo");
        DronePatrulha droneAereo = new DronePatrulha("DRN-300", "Hangar Base", true, "Patrulha Aérea ZNR-02", 100);

        CentralMonitoramento central = new CentralMonitoramento();
        central.registrarDispositivo(sensorCasaReator, casaReator);
        central.registrarDispositivo(sensorPatio, patioArmazenamento);
        central.registrarDispositivo(droneAereo, patioArmazenamento);

        System.out.println("\n CENÁRIO 1: Rotina Normal");
        sensorCasaReator.atualizarLeitura(20.0);
        imprimirResultadoAnalise(central.analisarLeituras());

        System.out.println("\n CENÁRIO 1.5: Nível de Atenção na Casa do Reator");
        sensorCasaReator.atualizarLeitura(48.5);

        imprimirResultadoAnalise(central.analisarLeituras());

        System.out.println("\n CENÁRIO 2: Pico de Radiação na Casa do Reator");
        sensorCasaReator.atualizarLeitura(75.5);
        imprimirResultadoAnalise(central.analisarLeituras());

        System.out.println("\n CENÁRIO 3: Acesso Indevido no Pátio");

        sensorCasaReator.atualizarLeitura(20.0);

        sensorPatio.detectarViolacao();
        droneAereo.acionarCameraVisaoComputacional();

        imprimirResultadoAnalise(central.analisarLeituras());

        System.out.println("           SISTEMA ENCERRADO COM SUCESSO         ");
    }

    private static void imprimirResultadoAnalise(List<AlertaSeguranca> alertas) {
        if (alertas.isEmpty()) {
            System.out.println("    [STATUS] Tudo OK. Nenhuma anomalia detectada.");
        } else {
            for (AlertaSeguranca alerta : alertas) {
                System.out.println("    " + alerta.toString());
            }
        }
    }
}