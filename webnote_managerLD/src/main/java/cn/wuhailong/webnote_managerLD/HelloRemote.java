/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_managerLD;



import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@FeignClient(name= "userProducer")
public interface HelloRemote {
    @RequestMapping("/getGenderDistribution")
    public Map<String, Double> getGenderDistribution();
}
