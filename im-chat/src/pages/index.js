import React, {useEffect, useState} from 'react';
import io from 'socket.io-client';
import Messages from './components/Messages';
import MessageInput from './components/MessageInput';
import OnlineList from './components/OnlineList';

import styles from './index.css';

function App() {
  const [socket, setSocket] = useState(null);

  useEffect(() => {
    const newSocket = io(`http://127.0.0.1:8888/ch_001?room=111111&channel=ch_001`, {
      transports:['websocket']
    });
    setSocket(newSocket);
    return () => newSocket.close();
  }, [setSocket]);

  return (
    <div className={styles.app}>
      <header className={styles.appHeader}>
        <div>Chat Demo</div>
      </header>
      {socket ? (
        <div className={styles.chat}>
          <div className="message-container">
            <Messages socket={socket}/>
            <MessageInput socket={socket}/>
          </div>
          <div className="user-container">
            <OnlineList socket={socket}/>
          </div>
        </div>
      ) : (
        <div>Not Connected</div>
      )}
    </div>
  );
}

export default App;
