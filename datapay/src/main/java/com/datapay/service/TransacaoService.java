package com.datapay.service;

import com.datapay.domain.Transacao;
import com.datapay.dto.TransacaoRequest;
import com.datapay.validations.Validacoes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new ArrayList<>();

    private List<Validacoes> validacoes = new ArrayList<>();

    public TransacaoService(List<Validacoes> validacoes) {
        this.validacoes = validacoes;
    }

    public void salvarRequest(TransacaoRequest transacaoRequest){
        validacoes.forEach(v-> v.validar(transacaoRequest));
        transacoes.add(new Transacao(transacaoRequest.valor(), transacaoRequest.dataHora()));
    }

    public void deletar() {
        transacoes.clear();
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
