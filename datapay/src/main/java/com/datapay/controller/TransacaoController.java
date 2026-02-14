package com.datapay.controller;

import com.datapay.dto.TransacaoRequest;
import com.datapay.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void salvarRequest(@RequestBody TransacaoRequest transacaoRequest)
    {
        service.salvarRequest(transacaoRequest);
        System.out.println(transacaoRequest);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletarRequest()
    {
        service.deletar();
    }

}
