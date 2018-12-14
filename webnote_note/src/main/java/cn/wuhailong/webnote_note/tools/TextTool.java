/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.tools;

/**
 * 文字处理工具类
 * @author Administrator
 */
public class TextTool {
    
    /**
     * 敏感字符和非法字符转换
     * @param text
     * @return
     */
    public static String textProcess(String text){
        text = text.replace("<", "[[");
        text = text.replace(">", "]]");
        return text;
    }
}
