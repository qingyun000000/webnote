/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_largeDataServiceProducer_user.controller;

import cn.wuhailong.webnote_largeDataServiceProducer_user.service.UserLargeDataService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
public class UserLargeDataController {
    
    @Autowired
    private UserLargeDataService service;
    
    @RequestMapping("/getGenderDistribution")
    public Map<String, Double> getGenderDistribution() {
        return service.getGenderDistribution();
    }
    
    @RequestMapping("/getAgeDistribution")
    public Map<String, Double> getAgeDistribution() {
        return service.getAgeDistribution();
    }
    
    @RequestMapping("/getMaxNickNameKeyword")
    public List<String> getMaxNickNameKeyword(){
        return service.getMaxNickNameKeyword();
    }
    
    @RequestMapping("/getRegTimeDistribution")
    public Map<String, Double> getRegTimeDistribution(){
        return service.getRegTimeDistribution();
    }
    
    
}
