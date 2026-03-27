package com.telecom.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.telecom.model.RechargeSoapRequest;
import com.telecom.model.RechargeSoapResponse;
import com.telecom.service.RechargeSoapService;

@Endpoint
public class RechargeEndpoint {

    private static final String NAMESPACE_URI = "http://telecom.com/recharge";

    private final RechargeSoapService service;

    public RechargeEndpoint(RechargeSoapService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RechargeSoapRequest")
    @ResponsePayload
    public RechargeSoapResponse recharge(@RequestPayload RechargeSoapRequest request) {
        return service.processRecharge(request);
    }
}