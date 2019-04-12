/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_largeDataServiceProducer_user.service.impl;

import cn.wuhailong.webnote_largeDataServiceProducer_user.dao.UserLargeDataDao;
import cn.wuhailong.webnote_largeDataServiceProducer_user.dao.UserSuppleInfoLargeDataDao;
import cn.wuhailong.webnote_largeDataServiceProducer_user.domain.pojo.User;
import cn.wuhailong.webnote_largeDataServiceProducer_user.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_largeDataServiceProducer_user.service.UserLargeDataService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class UserLargeDataServiceImpl implements UserLargeDataService{
    
    @Autowired
    private UserLargeDataDao userDao;
    
    @Autowired
    private UserSuppleInfoLargeDataDao infoDao;

    @Override
    public Map<String, Double> getGenderDistribution() {
        int menNumber = infoDao.countByGender("男");
        int womenNumber = infoDao.countByGender("女");
        int secrecyNumber = infoDao.countByGender("保密");
        int total = menNumber + womenNumber + secrecyNumber;
        double menPercent, womenPercent, secrecyPercent;
        if(total > 0){
            menPercent = menNumber / (double)total;
            womenPercent = womenNumber / (double)total;
            secrecyPercent = secrecyNumber / (double)total;
        }else{
            menPercent = 0;
            womenPercent = 0;
            secrecyPercent = 0;
        }
        
        
        Map<String, Double> result = new HashMap<>();
        result.put("男性人数", (double)menNumber);
        result.put("女性人数", (double)womenNumber);
        result.put("保密人数", (double)secrecyNumber);
        result.put("男性百分比", menPercent);
        result.put("女性百分比", womenPercent);
        result.put("保密百分比", secrecyPercent);
        
        return result;
    }

    @Override
    public Map<String, Double> getAgeDistribution() {
        List<UserSuppleInfo> userList = infoDao.findAll();
        Map<String, Double> result = new HashMap<>();
        for(UserSuppleInfo info : userList){
            Date birthday = info.getBirthday();
            if(birthday == null){
                continue;
            }
            
            Date now = new Date();
            int year = (int) ((now.getTime() - birthday.getTime())/(365.24 * 24 * 3600 * 1000));
            
            if(result.get(year + "岁") != null){
                result.put(year + "岁", result.get(year + "岁") + 1);
            }else{
                result.put(year + "岁", 1.0);
            }
        }
        double total = 0;
        Map<String, Double> percentResult = new HashMap<>();
        for(String key : result.keySet()){
            total = total + result.get(key);
            System.out.println(key + ":"  +result.get(key) );
            System.out.println(total);
        }
        if(total != 0){
            for(String key : result.keySet()){
                percentResult.put(key +"百分比", result.get(key)/total);
            }
        }else{
            for(String key : result.keySet()){
                percentResult.put(key +"百分比", 0.0);
            }
        }
        result.putAll(percentResult);
        return result;
    }

    @Override
    public Map<String, Double> getRegTimeDistribution() {
        List<User> userList = userDao.findAll();
        Map<String, Double> result = new HashMap<>();
        Calendar c = Calendar.getInstance();
        for(User user : userList){
            Date regTime = user.getRegTime();
            if(regTime == null){
                continue;
            }
            
            c.setTime(regTime);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            if(result.get(year + "年" + month + "月") != null){
                result.put(year + "年" + month + "月", result.get(year + "年" + month + "月") + 1);
            }else{
                result.put(year + "年" + month + "月", 1.0);
            }
        }
        double total = 0;
        Map<String, Double> percentResult = new HashMap<>();
        for(String key : result.keySet()){
            total = total + result.get(key);
            System.out.println(key + ":"  +result.get(key) );
            System.out.println(total);
        }
        if(total != 0){
            for(String key : result.keySet()){
                percentResult.put(key +"百分比", result.get(key)/total);
            }
        }else{
            for(String key : result.keySet()){
                percentResult.put(key +"百分比", 0.0);
            }
        }
        result.putAll(percentResult);
        return result;
    }

    @Override
    public List<String> getMaxNickNameKeyword() {
        List<User> userList = userDao.findAll();
        Map<String, Double> result = new HashMap<>();
        for(User user : userList){
            String nickName = user.getNickName();
            for(Character ch: nickName.toCharArray()){
                if(result.containsKey(ch.toString())){
                    result.put(ch.toString(), result.get(ch.toString()) + 1);
                }else{
                    result.put(ch.toString(), 1.0);
                }
            }
        }
        List<Map.Entry<String,Double>> list = new ArrayList<Map.Entry<String,Double>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Map.Entry<String, Double> o1,
                    Map.Entry<String, Double> o2) {
                return (int)(o2.getValue() - o1.getValue());
            }
            
        });
        List<String> resultList = new ArrayList<>();
        for(Map.Entry<String,Double> entry : list){
            resultList.add(entry.getKey());
            resultList.add(entry.getValue() + "");
        }
        return resultList;
    }
    

    
}
