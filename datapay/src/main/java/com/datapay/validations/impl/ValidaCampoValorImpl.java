package com.datapay.validations.impl;

import com.datapay.dto.TransacaoRequest;
import com.datapay.exceptions.ValorInvalidoException;
import com.datapay.validations.Validacoes;
import org.springframework.stereotype.Component;

@Component
public class ValidaCampoValorImpl implements Validacoes {
    @Override
    public void validar(TransacaoRequest transacaoRequest) {
        if(transacaoRequest.valor() < 0)
        {
            throw new ValorInvalidoException("O valor não pode ser menor que 0, tente novamente.");
        }
    }
}

