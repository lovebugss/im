# IM

应用于直播场景中的聊天室.

## 项目介绍

```
IM
|-- im-core 核心模块
|-- im-filter 过滤模块. 消息过滤
|-- im-gateway 网关服务
|-- im-server 所有业务逻辑相关
|-- im-conncet 维护长链接
|-- im-db
```
服务之间使用rpc进行通信,
////
## 难点1
connect服务要做到无状态, 每一台服务连接数均匀

## 难点2 
用户量巨大时, 消息广播困难

## 难点3
保证消息的有序性

## 端口号
////
