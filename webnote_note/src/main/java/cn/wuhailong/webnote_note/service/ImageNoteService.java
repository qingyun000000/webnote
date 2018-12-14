/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.service;

import cn.wuhailong.webnote_note.domain.pojo.ImageNote;
import cn.wuhailong.webnote_note.domain.dto.Page;
import cn.wuhailong.webnote_note.exception.ImageNoteNullException;
import java.util.List;

/**
 * 心情图片业务层接口
 * @author Administrator
 */
public interface ImageNoteService {

    /**
     * 获取心情图片分页信息
     * @param userId
     * @param page
     * @return
     */
    public List<ImageNote> getMyNotesByPage(Long userId, Page page);

    /**
     * 分页查询心情图片
     * @param userId
     * @param page
     * @return
     */
    public Page getMyNotesPage(Long userId, Page page);

    /**
     * 新增心情图片
     * @param imageNote
     */
    public void addImageNote(ImageNote imageNote);

    /**
     * 加载心情图片
     * @param id
     * @return
     */
    public ImageNote load(Long id);

    /**
     * 删除心情图片
     * @param imageNote
     * @throws ImageNoteNullException
     */
    public void delete(ImageNote imageNote) throws ImageNoteNullException;

    /**
     * 修改心情图片
     * @param imageNote
     * @throws ImageNoteNullException
     */
    public void updateImageNote(ImageNote imageNote) throws ImageNoteNullException;

    
}
