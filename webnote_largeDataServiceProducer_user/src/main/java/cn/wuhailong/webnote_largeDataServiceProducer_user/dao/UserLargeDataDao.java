/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_largeDataServiceProducer_user.dao;

import cn.wuhailong.webnote_largeDataServiceProducer_user.domain.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface UserLargeDataDao extends JpaRepository<User, Long>{
    
}
