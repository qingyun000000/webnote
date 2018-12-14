/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.dao;


import cn.wuhailong.webnote_manager.domain.pojo.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface UserManageDao extends JpaRepository<User, Long>{
    int countByUserNameLikeOrNickNameLike(String userName, String nickName);
    List<User> findByUserNameLikeOrNickNameLike(String userName, String nickName, Pageable pageable);
}
