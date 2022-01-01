import React, {useEffect, useState} from 'react';
import {Avatar, List} from 'antd';
import VirtualList from 'rc-virtual-list';
import './Messages.css';


function Messages({socket}) {
  const [messages, setMessages] = useState([{id: 1, value: "nihao", user: {name: "zhangsan"}}]);

  useEffect(() => {
    const messageListener = (message) => {
      setMessages((prevMessages) => {
        prevMessages.push(message)
        return prevMessages;
      });
    };

    const deleteMessageListener = (messageID) => {
      setMessages((prevMessages) => {
        return prevMessages.filter((value => value.id !== messageID))
      });
    };

    socket.on('message', messageListener);
    socket.on('deleteMessage', deleteMessageListener);
    socket.emit('getMessages');

    return () => {
      socket.off('message', messageListener);
      socket.off('deleteMessage', deleteMessageListener);
    };
  }, [socket]);

  return (
    <List>
      <VirtualList
        data={messages}
        height={400}
        itemHeight={47}
        itemKey="id"
        // onScroll={onScroll}
      >
        {item => (
          <List.Item key={item.id}>
            <List.Item.Meta
              avatar={<Avatar src={"https://joeschmoe.io/api/v1/random"}/>}
              title={item.user.name}
              description={item.value}
            />
          </List.Item>
        )}
      </VirtualList>
    </List>

  );
}

export default Messages;
