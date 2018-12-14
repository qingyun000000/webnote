/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.dao;


import cn.wuhailong.webnote_note.domain.pojo.Note;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文本笔记类持久层接口，JPA，继承JpaRepository
 * @author Administrator
 */
public interface NoteDao extends JpaRepository<Note, Long>{

    /**
     * 根据用户id分页查询文本笔记
     * @param userId
     * @param pageable
     * @return
     */
    List<Note> findByUserId(long userId, Pageable pageable);
    
    /**
     * 根据用户id查询文本笔记数量
     * @param userId
     * @return
     */
    Long countByUserId(long userId);
}
