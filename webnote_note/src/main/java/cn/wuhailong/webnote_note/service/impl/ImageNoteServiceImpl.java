/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.service.impl;

import cn.wuhailong.webnote_note.dao.ImageNoteDao;
import cn.wuhailong.webnote_note.domain.pojo.ImageNote;
import cn.wuhailong.webnote_note.domain.dto.Page;
import cn.wuhailong.webnote_note.exception.ImageNoteNullException;
import cn.wuhailong.webnote_note.exception.UserErrorException;
import cn.wuhailong.webnote_note.service.ImageNoteService;
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
import org.springframework.stereotype.Service;

/**
 * 心情图片业务类
 * @author Administrator
 */
@Service
public class ImageNoteServiceImpl implements ImageNoteService{
    
    @Autowired
    private ImageNoteDao imageDao;

    /**
     * 查询心情图片分页信息
     * @param userId
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public Page getMyNotesPage(Long userId, Page page) {
        int totalCount_image = imageDao.countByUserId(userId).intValue();
        int totalPage_image = (int) Math.ceil(totalCount_image /(double) page.getCountOfOnePage_image());
        page.setTotalCount_image(totalCount_image);
        page.setTotalPage_image(totalPage_image);
        return page;
    }
    
    /**
     * 分页查询心情图片
     * @param userId
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public List<ImageNote> getMyNotesByPage(Long userId, Page page) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page.getShowPage_image()-1, page.getCountOfOnePage_image(), sort);
        
        return imageDao.findByUserId(userId, pageable);
    }

    /**
     * 新增心情图片
     * @param imageNote
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void addImageNote(ImageNote imageNote) {
        
        //补充信息，保存
        imageNote.setCreatTime(new Date());
        imageNote.setUpdateTime(new Date());
        imageDao.save(imageNote);
        
        //输出日志，同于积分统计
        NoteLoggerTool.writeNoteLog(imageNote.getUserId() + "", "addImageNote");
    }

    /**
     * 加载心情图片
     * @param id
     * @return
     */
    @Override
    public ImageNote load(Long id) {
        return imageDao.getOne(id);
    }

    /**
     * 删除心情图片
     * @param imageNote
     * @throws cn.wuhailong.webnote_note.exception.ImageNoteNullException
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void delete(ImageNote imageNote, User user) throws ImageNoteNullException, UserErrorException {
        ImageNote get = imageDao.getOne(imageNote.getId());
        if(get == null){
            throw new ImageNoteNullException("心情图片不存在或者已被删除");
        }
        if(!Objects.equals(get.getUserId(), user.getId())){
            throw new UserErrorException("用户信息错误");
        }
        imageDao.delete(get);
    }
    
    /**
     * 修改心情图片
     * @param imageNote
     * @throws cn.wuhailong.webnote_note.exception.ImageNoteNullException
     */
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void updateImageNote(ImageNote imageNote, User user) throws ImageNoteNullException, UserErrorException {
        
        ImageNote oldImageNote = imageDao.getOne(imageNote.getId());
        
        if(oldImageNote == null){
            throw new ImageNoteNullException("心情图片不存在或者已被删除");
        }
        if(!Objects.equals(oldImageNote.getUserId(), user.getId())){
            throw new UserErrorException("用户信息错误");
        }
        //补充信息，保存
        oldImageNote.setUpdateTime(new Date());
        oldImageNote.setDescription(imageNote.getDescription());
        if(imageNote.getImgUrl() != null && (!"".equals(imageNote.getImgUrl()))){
            oldImageNote.setImgUrl(imageNote.getImgUrl());
        }
        imageDao.save(oldImageNote);
        
        //输出日志，同于积分统计
        NoteLoggerTool.writeNoteLog(imageNote.getUserId() + "", "updateImageNote");
    }
    
}
