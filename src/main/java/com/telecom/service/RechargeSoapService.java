package com.telecom.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.telecom.model.RechargeSoapRequest;
import com.telecom.model.RechargeSoapResponse;

@Service
public class RechargeSoapService {

    private static final Logger logger = LogManager.getLogger(RechargeSoapService.class);

    public RechargeSoapResponse processRecharge(RechargeSoapRequest request) {
        
        logger.info("Processing SOAP recharge for MSISDN: {} with amount: {}", 
                    request.getMsisdn(), request.getAmount());

        RechargeSoapResponse response = new RechargeSoapResponse();
        response.setStatus("SUCCESS");
        response.setMessage(
            "Recharge successful for " + request.getMsisdn()
            + " with amount " + request.getAmount()
        );

        logger.info("SOAP recharge processed successfully - Response status: {}", response.getStatus());
        return response;
    }
}
