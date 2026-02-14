package com.datapay.validations.impl;

import com.datapay.dto.TransacaoRequest;
import com.datapay.exceptions.DataHoraInvalidaException;
import com.datapay.validations.Validacoes;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ValidaDataHoraImpl implements Validacoes {
    @Override
    public void validar(TransacaoRequest transacaoRequest)
    {
        boolean dataFuturaInvalida = transacaoRequest.dataHora().isAfter(OffsetDateTime.now());

        if(dataFuturaInvalida)
        {
            throw new DataHoraInvalidaException("A data atual é inválida!");
        }
    }
}
