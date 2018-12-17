/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息类
 * @author Administrator
 */
public class Page {
    
    //笔记分页信息（主页显示默认每页5条）
    private int totalCount;                //笔记总条数
    private int totalPage;                 //笔记总页数
    private int showPage;                  //笔记当前显示页
    private int countOfOnePage = 5;        //笔记每页显示条数

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

    /**
     * 获取笔记要显示的所有页码
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
