/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.tools;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 富文本笔记模块输出日志工具类-暂只实现新增和修改记录（用于积分模块计算积分)
 * @author Administrator
 */
public class RichNoteLoggerTool {
    
    private static final Logger NOTELOGGER = LoggerFactory.getLogger(RichNoteLoggerTool.class);
    
    /**
     * 输出笔记系统日志（用户id + 动作）
     * @param userId
     * @param action
     */
    public static void writeNoteLog(String userId, String action){
        String info = "userid:" +userId + "  action:" + action;
        NOTELOGGER.info(info);
    }
    
    /**
     * 输出笔记系统日志（用户id + 动作 + 参数）
     * @param userId
     * @param action
     * @param params
     */
    public static void writeNoteLog(String userId, String action, Map<String, String> params){
        StringBuilder info = new StringBuilder("userid:" +userId + "  action:" + action + "  params{");
        if(params == null || params.isEmpty()){
            for(int n = 0; n < 9; n++){
                info.deleteCharAt(info.length() - 1);
            }
        }else{
            for(String key : params.keySet()){
                info.append(key).append(":").append(params.get(key)).append(",");
            }
            info.deleteCharAt(info.length() - 1);
            info.append("}");
        }
        NOTELOGGER.info(info.toString());
    }
    
}
