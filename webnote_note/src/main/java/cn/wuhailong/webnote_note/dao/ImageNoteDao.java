/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.dao;

import cn.wuhailong.webnote_note.domain.pojo.ImageNote;
import cn.wuhailong.webnote_note.domain.pojo.Note;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 心情图片类持久层接口，JPA，继承JpaRepository
 * @author Administrator
 */
public interface ImageNoteDao extends JpaRepository<ImageNote, Long>{

    /**
     * 根据用户id分页查找心情图片
     * @param userId
     * @param pageable
     * @return
     */
    List<ImageNote> findByUserId(long userId, Pageable pageable);
    
    /**
     * 根据用户id查询心情图片的数量
     * @param userId
     * @return
     */
    Long countByUserId(long userId);
}
