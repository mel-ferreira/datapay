package com.datapay.dto;

import java.time.OffsetDateTime;

public record TransacaoRequest (Double valor, OffsetDateTime dataHora){
}
