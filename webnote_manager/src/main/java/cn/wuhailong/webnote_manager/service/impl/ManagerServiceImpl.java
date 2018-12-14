/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.service.impl;

import cn.wuhailong.webnote_manager.dao.ManagerDao;
import cn.wuhailong.webnote_manager.domain.pojo.ManagerListPage;
import cn.wuhailong.webnote_manager.domain.pojo.Manager;
import cn.wuhailong.webnote_manager.exception.ManagerExistException;
import cn.wuhailong.webnote_manager.exception.ManagerNullException;
import cn.wuhailong.webnote_manager.exception.PasswordErrorException;
import org.springframework.stereotype.Service;
import cn.wuhailong.webnote_manager.service.ManagerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Administrator
 */
@Service
public class ManagerServiceImpl implements ManagerService{
    
//    private static cn.wuhailong.webnote_usermanagerservice.iface.UserManagerService dubboService;
//    
//    static {
//        ApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"application-client.xml"});
//        dubboService = (cn.wuhailong.webnote_usermanagerservice.iface.UserManagerService) app.getBean("userManagerServiceDubbo");
//    }
//
//    @Override
//    public Object getMessage() {
//        return "myService = " + dubboService.doMyTest("1234", "abcde");
//    }    
    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager login(Manager manager) throws ManagerNullException{
        Manager get = managerDao.findByManagerNameAndManagerPassword(manager.getManagerName(), manager.getManagerPassword());
        if( get == null ){
            throw new ManagerNullException("管理员账户不存在,或者密码错误");
        }
        return get;
    }
    
    @Override
    public List<Manager> getAllManager() {
        Sort sort = Sort.by(Sort.Direction.ASC, "managerName");
        return managerDao.findAll(sort);
    }

    @Override
    public void add(Manager manager) throws ManagerExistException{
        Manager get = managerDao.findByManagerName(manager.getManagerName());
        if(get != null){
            throw new ManagerExistException("账户已存在，不能重复");
        }
        managerDao.save(manager);
        
    }

    @Override
    public String delete(Manager manager) throws ManagerNullException {
        Manager get = managerDao.getOne(manager.getManagerId());
        if(get == null){
            throw new ManagerNullException("管理员不存在！！");
        }
        if("admin".equals(get.getManagerName())){
            throw new ManagerNullException("超级管理员不能被删除！！");
        }
        String managerName = get.getManagerName();
        managerDao.deleteById(manager.getManagerId());
        return managerName;
    }

    @Override
    public Boolean verPassword(Long managerId, String managerPassword) {
        Manager get = managerDao.getOne(managerId);
        if(get == null){
            return false;
        }
        if(managerPassword.equals(get.getManagerPassword())){
            return true;
        }
        return false;
    }

    @Override
    public String updatePassword(Long managerId, String managerPassword, String managerPasswordNew) throws ManagerNullException,PasswordErrorException {
        Manager get = managerDao.getOne(managerId);
        if(get == null){
            throw new ManagerNullException("无法修改密码，因为管理员不存在！！");
        }
        if(!managerPassword.equals(get.getManagerPassword())){
            throw new PasswordErrorException("原密码错误");
        }
        get.setManagerPassword(managerPasswordNew);
        managerDao.save(get);
        return get.getManagerName();
    }

    @Override
    public Boolean VerManagerNameAdd(String managerName) {
        Manager get = managerDao.findByManagerName(managerName);
        if(get != null){
            return false;
        }
        return true;
    }

    @Override
    public ManagerListPage getManagerListPage(ManagerListPage page) {
        int totalCount = (int) managerDao.count();
        int totalPage = (int) Math.ceil(totalCount /(double) page.getCountOfOnePage());
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        return page;
    }

    @Override
    public List<Manager> getManagerListByPage(ManagerListPage page) {
        //排序：修改时间倒序，最新的在最前面
	Sort sort = new Sort(Sort.Direction.ASC, "managerName");
        
        //分页，这里的第一个参数：当前页，是从0开始的
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        //查询结果返回
        return managerDao.findAll(pageable).getContent();
    }
    
}
