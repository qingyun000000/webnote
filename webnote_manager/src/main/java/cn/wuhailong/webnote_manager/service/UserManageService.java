/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.service;

import cn.wuhailong.webnote_manager.domain.pojo.User;
import cn.wuhailong.webnote_manager.domain.pojo.UserListPage;
import cn.wuhailong.webnote_manager.domain.pojo.UserSuppleInfo;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface UserManageService {

    public int getAllUserCount();

    public UserListPage getUserListPage(UserListPage page, String gender, String keyword);

    public List<User> getUserListByPage(UserListPage page, String gender, String keyword);

    public User loadUser(Long id);

    public UserSuppleInfo loadUserSuppleInfo(Long userId);

    public int getCountByGender(String gender);
    
}
