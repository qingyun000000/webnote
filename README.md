# webnote
Spring Boot 的Demo: 基于redis共享session的微应用架构-basic部分
基础部分包含4个子工程：
   webnote_user : 用户模块
   webnote_note : 笔记模块
   webnote_manager : 管理模块
   webnote_RTNote: 富文本模块
积分模块，包含KafKa的日志消息队列，Spring Boot 定时任务，RMI远程方法调用。 4个子项目： 
   webnote_score_loggerKafkaProducer ： KafKa消息生产者，运行于webnote_note服务器上，用于读取日志记录，上传消息至KafKa
   webnote_score_loggerKafkaConsumer ： Kafka消息消费者，独立运行，用于接收Kafka消息，并处理。
   webnote_scoreService : 积分模块的业务层和持久层，提供远程方法调用，统一积分业务。
   webnote_score : 积分模块controller层和前端，用于查询和显示用户积分，链接入口在webnote_note的list页面。

