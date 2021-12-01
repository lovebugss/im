package com.itrjp.im.server;

import com.itrjp.im.server.server.SocketIoServer;

/**
 * 程序主入口
 *
 * @author renjp
 */
public class ServerMain {
    public static void main(String[] args) {
        SocketIoServer.run(args);
    }
}
