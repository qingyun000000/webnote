/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息类
 * @author Administrator
 */
public class Page {
    
    //文本笔记分页信息（主页显示默认每页5条）
    private int totalCount;                //文本笔记总条数
    private int totalPage;                 //文本笔记总页数
    private int showPage;                  //文本笔记当前显示页
    private int countOfOnePage = 5;        //文本笔记每页显示条数
    
    //心情图片分页信息（主页显示默认每页3条）
    private int totalCount_image;          //心情图片总条数
    private int totalPage_image;           //心情图片总页数
    private int showPage_image;            //心情图片当前显示页
    private int countOfOnePage_image = 3;  //心情图片每页显示条数

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

    public int getTotalCount_image() {
        return totalCount_image;
    }

    public void setTotalCount_image(int totalCount_image) {
        this.totalCount_image = totalCount_image;
    }

    public int getTotalPage_image() {
        return totalPage_image;
    }

    public void setTotalPage_image(int totalPage_image) {
        this.totalPage_image = totalPage_image;
    }

    public int getShowPage_image() {
        return showPage_image;
    }

    public void setShowPage_image(int showPage_image) {
        this.showPage_image = showPage_image;
    }

    public int getCountOfOnePage_image() {
        return countOfOnePage_image;
    }

    public void setCountOfOnePage_image(int countOfOnePage_image) {
        this.countOfOnePage_image = countOfOnePage_image;
    }
    
    /**
     * 获取文本笔记要显示的所有页码
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
    
    /**
     * 获取心情图片要显示的所有页码
     * @return
     */
    public List<Integer> getAllPageImage(){
        List<Integer> pages = new ArrayList<>();
        int num = 0;
        for(int i= showPage_image - 2; i <= totalPage_image; i++){
            if(i > 0 && num < 5){
                pages.add(i);
                num++;
            }
        }
        return pages; 
    }

    
    
}
