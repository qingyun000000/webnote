/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.service;

import cn.wuhailong.webnote_RTNote.domain.dto.Page;
import cn.wuhailong.webnote_RTNote.domain.pojo.RichNote;
import java.util.List;

/**
 * 富文本笔记模块业务层接口
 * @author Administrator
 */
public interface RichNoteService {

    /**
     * 新增富文本笔记
     * @param note
     */
    public void addRichNote(RichNote note);

    /**
     * 查询富文本笔记分页信息
     * @param userId
     * @param page
     * @return
     */
    public Page getMyNotesPage(Long userId, Page page);

    /**
     * 分页查询富文本笔记
     * @param userId
     * @param page
     * @return
     */
    public List<RichNote> getMyNotesByPage(Long userId, Page page);

    /**
     * 加载笔记信息
     * @param id
     * @return
     */
    public RichNote load(Long id);
}
