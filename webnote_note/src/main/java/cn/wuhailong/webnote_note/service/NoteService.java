/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.service;


import cn.wuhailong.webnote_note.domain.pojo.Note;
import cn.wuhailong.webnote_note.domain.dto.Page;
import cn.wuhailong.webnote_note.exception.NoteNullException;
import java.util.List;


/**
 * 文本笔记业务层接口
 * @author Administrator
 */
public interface NoteService {
    
    /**
     * 获取文本笔记分页信息
     * @param userId
     * @param page
     * @return
     */
    public Page getMyNotesPage(Long userId, Page page);

    /**
     * 分页查询文本笔记
     * @param userId
     * @param page
     * @return
     */
    public List<Note> getMyNotesByPage(Long userId, Page page);

    /**
     * 新增文本笔记
     * @param note
     */
    public void add(Note note);

    /**
     * 加载文本笔记
     * @param id
     * @return
     */
    public Note load(Long id);

    /**
     * 修改文本笔记
     * @param note
     * @throws NoteNullException
     */
    public void update(Note note) throws NoteNullException;

    /**
     * 删除文本笔记
     * @param note
     * @throws NoteNullException
     */
    public void delete(Note note) throws NoteNullException;
    
}
