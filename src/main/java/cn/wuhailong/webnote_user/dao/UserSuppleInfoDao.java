/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.dao;

import cn.wuhailong.webnote_user.domain.pojo.UserSuppleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户扩展信息类持久层接口，JPA，继承JpaRepository
 * @author Administrator
 */
public interface UserSuppleInfoDao extends JpaRepository<UserSuppleInfo, Long>{

    /**
     * 根据用户Id查找用户扩展信息
     * @param userId
     * @return
     */
    UserSuppleInfo findByUserId(Long userId);
}
