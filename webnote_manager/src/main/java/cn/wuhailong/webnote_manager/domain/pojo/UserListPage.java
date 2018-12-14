/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.domain.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息类
 * @author Administrator
 */
public class UserListPage {
    
    //管理员列表分页信息（主页显示默认每页5条）
    private int totalCount;                //总条数
    private int totalPage;                 //总页数
    private int showPage;                  //当前显示页
    private int countOfOnePage = 16;       //每页显示条数
    private List<User> userList;           //用户列表
    

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getShowPage() {
        return showPage;
    }

    public void setShowPage(int showPage) {
        this.showPage = showPage;
    }

    public int getCountOfOnePage() {
        return countOfOnePage;
    }

    public void setCountOfOnePage(int countOfOnePage) {
        this.countOfOnePage = countOfOnePage;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    
    /**
     * 要显示的所有页码
     * @return
     */
    public List<Integer> getAllPage(){
        List<Integer> pages = new ArrayList<>();
        int num = 0;
        for(int i= showPage - 2; i <= totalPage; i++){
            if(i > 0 && num < 5){
                pages.add(i);
                num++;
            }
        }
        return pages; 
    }
  

    
    
}
