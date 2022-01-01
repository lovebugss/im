import React, {useEffect, useState} from 'react';
import {Avatar, List} from 'antd';
import VirtualList from 'rc-virtual-list';


function OnlineList({socket}) {
  const [onLineList, setOnLineList] = useState([
    {id: 1, user: {name: "zhangsan"}},
  ]);

  useEffect(() => {
    const onlineListener = (message) => {
      setOnLineList((prevMessages) => {
        prevMessages.push(message)
        return prevMessages;
      });
    };

    const deleteOnlineListener = (messageID) => {
      setOnLineList((prevMessages) => {
        return prevMessages.filter((value => value.id !== messageID))
      });
    };

    socket.on('userList', onlineListener);
    socket.on('deleteMessage', deleteOnlineListener);
    socket.emit('getMessages');

    return () => {
      socket.off('userList', onlineListener);
      socket.off('deleteMessage', deleteOnlineListener);
    };
  }, [socket]);

  return (
    <List>
      <VirtualList
        data={onLineList}
        height={400}
        itemHeight={47}
        itemKey="id"
        // onScroll={onScroll}
      >
        {item => (
          <List.Item key={item.id}>
            <List.Item.Meta
              avatar={<Avatar src={"https://joeschmoe.io/api/v1/random"}/>}
              title={item.name}
            />
          </List.Item>
        )}
      </VirtualList>
    </List>

  );
}

export default OnlineList;
