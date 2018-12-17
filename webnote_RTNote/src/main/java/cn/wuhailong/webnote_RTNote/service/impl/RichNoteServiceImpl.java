/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.service.impl;

import cn.wuhailong.webnote_RTNote.dao.RichNoteDao;
import cn.wuhailong.webnote_RTNote.domain.dto.Page;
import cn.wuhailong.webnote_RTNote.domain.pojo.RichNote;
import cn.wuhailong.webnote_RTNote.service.RichNoteService;
import cn.wuhailong.webnote_RTNote.tools.RichNoteLoggerTool;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 富文本笔记模块业务层实现
 * @author Administrator
 */
@Service
public class RichNoteServiceImpl implements RichNoteService{

    @Autowired
    private RichNoteDao richNoteDao;
    
    @Override
    public void addRichNote(RichNote note) {
        //补充信息，保存
        note.setCreatTime(new Date());
        note.setUpdateTime(new Date());
        richNoteDao.save(note);
        
        //输出日志，同于积分统计
        RichNoteLoggerTool.writeNoteLog(note.getUserId() + "", "addImageNote");
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public Page getMyNotesPage(Long userId, Page page) {
        int totalCount_image = richNoteDao.countByUserId(userId).intValue();
        int totalPage_image = (int) Math.ceil(totalCount_image /(double) page.getCountOfOnePage());
        page.setTotalCount(totalCount_image);
        page.setTotalPage(totalPage_image);
        return page;
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public List<RichNote> getMyNotesByPage(Long userId, Page page) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        return richNoteDao.findByUserId(userId, pageable);
    }
    
    public RichNote load(Long id){
        return richNoteDao.getOne(id);
    }
    
    
}
