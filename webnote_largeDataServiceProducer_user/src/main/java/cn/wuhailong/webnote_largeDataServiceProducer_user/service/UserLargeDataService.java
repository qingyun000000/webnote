/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_largeDataServiceProducer_user.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface UserLargeDataService {

    public Map<String, Double> getGenderDistribution();

    public Map<String, Double> getAgeDistribution();

    public Map<String, Double> getRegTimeDistribution();

    public List<String> getMaxNickNameKeyword();
    
}
