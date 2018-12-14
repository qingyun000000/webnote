/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.tools;

import cn.wuhailong.webnote_note.domain.vo.NoteResult;
import java.util.regex.Pattern;

/**
 * 笔记模块校验工具类
 * @author Administrator
 */
public class NoteVerTool {
    
    /**
     * 校验文本笔记标题
     * @param noteTitle
     * @param result
     */
    public static void VerNoteTitle(String noteTitle, NoteResult result){
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s\\.\\,\\/\\\\@\\#\\$%……&\\*!\\?\\p{P}]{1,20}$",noteTitle);
        if(!matches){
            result.setNoteTitleError("限1-20个汉字、字母、数字和标点");
        }
    }
    
    /**
     * 校验文本笔记内容
     * @param content
     * @param result
     */
    public static void VerContent(String content, NoteResult result){
        if(content.length() > 1000){
            result.setContentError("限1000个字符，大小超出");
        }
    }
    
    /**
     * 校验心情图片描述
     * @param description
     * @param result
     */
    public static void VerDescription(String description, NoteResult result){
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s\\.\\,\\/\\\\@\\#\\$%……&\\*!\\?\\p{P}]{0,100}$",description);
        if(!matches){
            result.setDescriptionError("限100个汉字、字母、数字和标点");
        }
    }
}
