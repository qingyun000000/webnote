/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.dao;

import cn.wuhailong.webnote_manager.domain.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface ManagerDao extends JpaRepository<Manager, Long>{
    
    Manager findByManagerNameAndManagerPassword(String managerName, String managerPassword);
    
    Manager findByManagerName(String managerName);
}
