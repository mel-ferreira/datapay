package com.datapay.service;

import com.datapay.domain.Transacao;
import com.datapay.dto.EstatisticaResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {

    private final TransacaoService service;

    public EstatisticaService(TransacaoService service) {
        this.service = service;
    }
    public EstatisticaResponse obterMetricas() {
        OffsetDateTime ultimos60seg = OffsetDateTime.now().minusSeconds(60);
        List<Transacao> filtroTransacoes = service.getTransacoes().stream().filter(t-> t.getDataHora().isAfter(ultimos60seg)).toList();

        DoubleSummaryStatistics metricas = filtroTransacoes.stream().mapToDouble(Transacao::getValor).summaryStatistics();

        double minValue = metricas.getCount() > 0 ? metricas.getMin() : 0.0;
        double maxValue = metricas.getCount() > 0 ? metricas.getMax() : 0.0;
        return new EstatisticaResponse(
                metricas.getCount(),
                metricas.getSum(),
                metricas.getAverage(),
                minValue,
                maxValue
        );
    }
}
