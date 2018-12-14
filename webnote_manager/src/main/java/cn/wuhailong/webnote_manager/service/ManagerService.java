/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.service;

import cn.wuhailong.webnote_manager.domain.pojo.ManagerListPage;
import cn.wuhailong.webnote_manager.domain.pojo.Manager;
import cn.wuhailong.webnote_manager.exception.ManagerExistException;
import cn.wuhailong.webnote_manager.exception.ManagerNullException;
import cn.wuhailong.webnote_manager.exception.PasswordErrorException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ManagerService {

    public Manager login(Manager manager) throws ManagerNullException;

    public List<Manager> getAllManager();

    public void add(Manager manager) throws ManagerExistException;

    public String delete(Manager manager) throws ManagerNullException;

    public Boolean verPassword(Long managerId, String managerPassword);

    public String updatePassword(Long managerId, String managerPassword, String managerPasswordNew) throws ManagerNullException,PasswordErrorException;

    public Boolean VerManagerNameAdd(String managerName);

    public ManagerListPage getManagerListPage(ManagerListPage page);

    public List<Manager> getManagerListByPage(ManagerListPage page);

    
}
