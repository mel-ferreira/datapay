package com.datapay.controller;

import com.datapay.dto.EstatisticaResponse;
import com.datapay.service.EstatisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService service;

    public EstatisticaController(EstatisticaService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public EstatisticaResponse obterMetricas()
    {
        service.obterMetricas();
    }
}
