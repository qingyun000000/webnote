/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_managerLD;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;
    
    @RequestMapping("/hello")
    public Map<String, Double> index() {
        return HelloRemote.getGenderDistribution();
    }

}
