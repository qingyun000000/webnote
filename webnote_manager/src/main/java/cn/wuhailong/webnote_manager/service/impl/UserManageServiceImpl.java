/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.service.impl;

import cn.wuhailong.webnote_manager.dao.UserManageDao;
import cn.wuhailong.webnote_manager.dao.UserSIManageDao;
import cn.wuhailong.webnote_manager.domain.pojo.User;
import cn.wuhailong.webnote_manager.domain.pojo.UserListPage;
import cn.wuhailong.webnote_manager.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_manager.service.UserManageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class UserManageServiceImpl implements UserManageService{
    
    @Autowired
    private UserManageDao userManageDao;
    
    @Autowired
    private UserSIManageDao siManageDao;

    @Override
    public int getAllUserCount() {
        return (int) userManageDao.count();
    }

    @Override
    public UserListPage getUserListPage(UserListPage page, String gender, String keyword) {
        int totalCount = 0;
        if(keyword != null && (!"".equals(keyword))){
            totalCount = (int) userManageDao.countByUserNameLikeOrNickNameLike("%"+ keyword + "%", "%"+ keyword + "%");
        }else if(gender != null && (!"".equals(gender))){
            totalCount = (int) siManageDao.countByGender(gender);
        }else{
            totalCount = (int) userManageDao.count();
        }
        int totalPage = (int) Math.ceil(totalCount /(double) page.getCountOfOnePage());
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    @Override
    public List<User> getUserListByPage(UserListPage page, String gender, String keyword) {
        if(keyword != null && (!"".equals(keyword))){
            return getUserListByKeyword(page, keyword);
        }else if(gender != null && (!"".equals(gender))){
            return getUserListByGender(page, gender);
        }else{
            return getUserListByPage(page);
        }
    }

    private List<User> getUserListByPage(UserListPage page) {
        //排序：修改时间倒序，最新的在最前面
	Sort sort = new Sort(Sort.Direction.ASC, "userName");
        
        //分页，这里的第一个参数：当前页，是从0开始的
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        //查询结果返回
        return userManageDao.findAll(pageable).getContent();
    }
    
    private List<User> getUserListByKeyword(UserListPage page, String keyword) {
        //排序：修改时间倒序，最新的在最前面
	Sort sort = new Sort(Sort.Direction.ASC, "userName");
        
        //分页，这里的第一个参数：当前页，是从0开始的
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        //查询结果返回
        return userManageDao.findByUserNameLikeOrNickNameLike("%"+ keyword + "%", "%"+ keyword + "%", pageable);
    }
    
    private List<User> getUserListByGender(UserListPage page, String gender) {
        //排序：修改时间倒序，最新的在最前面
	Sort sort = new Sort(Sort.Direction.ASC, "userId");
        
        //分页，这里的第一个参数：当前页，是从0开始的
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        //查询结果返回
        List<UserSuppleInfo> infos = siManageDao.findByGender(gender, pageable);
        
        List<User> users = new ArrayList<>();
        for(UserSuppleInfo info : infos){
            //这里不能用getOne,因为它延迟加载，而我们并没有将user直接使用，而是放数组了
            User user = userManageDao.findById(info.getUserId()).get();
            users.add(user);
        }
        
        return users;
    }

    @Override
    public User loadUser(Long id) {
        return userManageDao.getOne(id);
    }

    @Override
    public UserSuppleInfo loadUserSuppleInfo(Long userId) {
        return siManageDao.findByUserId(userId);
    }

    @Override
    public int getCountByGender(String gender) {
        if("男".equals(gender) || "女".equals(gender)){
            return siManageDao.countByGender(gender);
        }else{
            return siManageDao.countByGender("保密");
        }
        
    }

    
    
}
