package com.datapay.validations.impl;

import com.datapay.dto.TransacaoRequest;
import com.datapay.exceptions.FormsInvalidoException;
import com.datapay.validations.Validacoes;
import org.springframework.stereotype.Component;

@Component
public class ValidarCamposPreenchidosImpl implements Validacoes {
    @Override
    public void validar(TransacaoRequest transacaoRequest)
    {
        if(transacaoRequest.valor() == null || transacaoRequest.dataHora() == null)
        {
            throw new FormsInvalidoException("O campo valor ou data não foram preenchidos, por favor, tente novamente.");
        }
    }
}
