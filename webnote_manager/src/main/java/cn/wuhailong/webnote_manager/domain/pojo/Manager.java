/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.domain.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Administrator
 */
@Entity
public class Manager implements Serializable{
    
    private static final long serialVersionUID = 3001L;
    
    @Id
    @GeneratedValue
    private Long managerId;
    
    @Column(nullable = false, unique = true)
    private String managerName;
    
    @Column(nullable = false)
    private String managerPassword;
    
    @Transient
    private String managerPassword2;
     
    @Transient
    private String managerPasswordNew;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerPassword2() {
        return managerPassword2;
    }

    public void setManagerPassword2(String managerPassword2) {
        this.managerPassword2 = managerPassword2;
    }

    public String getManagerPasswordNew() {
        return managerPasswordNew;
    }

    public void setManagerPasswordNew(String managerPasswordNew) {
        this.managerPasswordNew = managerPasswordNew;
    }
    
    
}
