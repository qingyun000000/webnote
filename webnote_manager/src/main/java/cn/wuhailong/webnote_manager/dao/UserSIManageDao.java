/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.dao;

import cn.wuhailong.webnote_manager.domain.pojo.UserSuppleInfo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface UserSIManageDao extends JpaRepository<UserSuppleInfo, Long>{
    
    UserSuppleInfo findByUserId(Long userId);
    
    int countByGender(String gender);
    
    List<UserSuppleInfo> findByGender(String gender, Pageable pageable);
}
