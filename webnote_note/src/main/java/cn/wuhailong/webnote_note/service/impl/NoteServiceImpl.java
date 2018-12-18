/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.service.impl;


import cn.wuhailong.webnote_note.dao.NoteDao;
import cn.wuhailong.webnote_note.domain.pojo.Note;
import cn.wuhailong.webnote_note.domain.dto.Page;
import cn.wuhailong.webnote_note.exception.NoteNullException;
import cn.wuhailong.webnote_note.exception.UserErrorException;
import cn.wuhailong.webnote_note.service.NoteService;
import cn.wuhailong.webnote_note.tools.NoteLoggerTool;
import cn.wuhailong.webnote_user.domain.pojo.User;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * 文本笔记业务类
 * @author Administrator
 */
@Service
public class NoteServiceImpl implements NoteService{
    
    @Autowired
    private NoteDao noteDao;

    /**
     * 查询文本笔记分页信息
     * @param userId
     * @param page
     * @return
     */
    @Override
    public Page getMyNotesPage(Long userId, Page page) {
        int totalCount = noteDao.countByUserId(userId).intValue();
        int totalPage = (int) Math.ceil(totalCount /(double) page.getCountOfOnePage());
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        return page;
    }
    
    /**
     * 分页查询文本笔记
     * @param userId
     * @param page
     * @return
     */
    @Override
    public List<Note> getMyNotesByPage(Long userId, Page page) {
        
        //排序：修改时间倒序，最新的在最前面
	Sort sort = new Sort(Direction.DESC, "updateTime");
        
        //分页，这里的第一个参数：当前页，是从0开始的
        Pageable pageable = new PageRequest(page.getShowPage()-1, page.getCountOfOnePage(), sort);
        
        //查询结果返回
        return noteDao.findByUserId(userId, pageable);
    }

    /**
     * 新增文本笔记
     * @param note
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void add(Note note) {
        
        note.setCreatTime(new Date());
        note.setUpdateTime(new Date());
        
        noteDao.save(note);
        NoteLoggerTool.writeNoteLog(note.getUserId() + "", "addTextNote");
    }

    /**
     * 加载文本笔记
     * @param id
     * @return
     */
    @Override
    public Note load(Long id) {
        return noteDao.getOne(id);
    }

    /**
     * 修改文本笔记
     * @param note
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void update(Note note, User user) throws NoteNullException, UserErrorException {
        
        //获取，判断
        Note oldnote = noteDao.getOne(note.getId());
        if(oldnote == null){
            throw new NoteNullException("笔记不存在或者已被删除");
        }
        if(!Objects.equals(oldnote.getUserId(), user.getId())){
            throw new UserErrorException("用户信息错误");
        }    
        //补充信息，保存
        note.setCreatTime(oldnote.getCreatTime());
        note.setUserId(oldnote.getUserId());
        note.setUpdateTime(new Date());
        noteDao.save(note);
        
        //输出日志，用于积分统计
        NoteLoggerTool.writeNoteLog(note.getUserId() + "", "updateTextNote");
    }

    /**
     * 删除文本笔记
     * @param note
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void delete(Note note, User user) throws NoteNullException, UserErrorException {
        Note get = noteDao.getOne(note.getId());
        if(get == null){
            throw new NoteNullException("笔记不存在或者已被删除");
        }
        if(!Objects.equals(get.getUserId(), user.getId())){
            throw new UserErrorException("用户信息错误");
        }
        noteDao.delete(get);
    }

    
    
    
}
