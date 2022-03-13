import ws from 'k6/ws';
import {check} from 'k6';

export const options = {
    stages: [{}],
    thresholds: {},
    vus: 10,
    duration: '30s'

}

export default function () {
    const url = 'ws://localhost:8888/ws?roomId=r_12345678';
    const params = {roomId: 'r_01234567'};

    const response = ws.connect(url, {}, function (socket) {
        socket.on('open', function open() {
            console.log('connected');
            socket.send(Date.now());

            socket.setInterval(function timeout() {
                socket.ping();
                console.log('Pinging every 1sec (setInterval test)');
            }, 30000);
        });

        socket.on('ping', () => console.log('PING!'));
        socket.on('pong', () => console.log('PONG!'));
        socket.on('pong', () => {
            // Multiple event handlers on the same event
            console.log('OTHER PONG!');
        });

        socket.on('close', () => console.log('disconnected'));

        socket.on('error', (e) => {
            if (e.error() != 'websocket: close sent') {
                console.log('An unexpected error occurred: ', e.error());
            }
        });

        // socket.setTimeout(function () {
        //     console.log('2 seconds passed, closing the socket');
        //     socket.close();
        // }, 2000);
    });

    check(response, {'status is 101': (r) => r && r.status === 101});
}
