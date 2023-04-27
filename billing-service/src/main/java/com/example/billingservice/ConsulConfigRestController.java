package com.example.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RefreshScope
public class ConsulConfigRestController {

    @Autowired
    private MyConsulConfig myConsulConfig;

    @Autowired
    private MyVaultConfig myVaultConfig;

    @GetMapping("/myConfig")
    public Map<String,Object> myconfig() {

        return Map.of("consulCofig",myConsulConfig,"vaultConfig",myVaultConfig);
    }



   /* @Value("${token.accessTokenTimeOut}")
    private long accessTokenTimeOut;
    @Value("${token.refreshTokenTimeOut}")
    private long refreshTokenTimeOut;

    @GetMapping("/myConfig")
    public Map<String,Object> myconfig(){

        return Map.of("accessTokenTimeOut",accessTokenTimeOut,"refreshTokenTimeOut",refreshTokenTimeOut);
    }*/
}
