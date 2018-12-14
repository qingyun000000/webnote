/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.dao;


import cn.wuhailong.webnote_user.domain.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户类持久层接口，JPA，继承JpaRepository
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, Long>{

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);
}
