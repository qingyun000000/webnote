/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.dao;

import cn.wuhailong.webnote_RTNote.domain.pojo.RichNote;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RichNote持久层接口，继承JpaRepository
 * @author Administrator
 */
public interface RichNoteDao extends JpaRepository<RichNote, Long>{
        
    /**
     * 查询用户的笔记总条数
     * @param userId
     * @return
     */
    Long countByUserId(Long userId);

    /**
     * 根据用户名分页查找笔记
     * @param userId
     * @param pageable
     * @return
     */
    List<RichNote> findByUserId(Long userId, Pageable pageable);
}
