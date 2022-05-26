import { Stomp } from "@stomp/stompjs";
import SockJS from 'sockjs-client';

export default class {

    constructor(action) {
        this.action = action;
        this.stompClient = null;
        this.socket = null;
        this.args = [];
        this.connect();
        this.handlerFunction = ()=>{};
        this.errorHandlerFunction = ()=>{};

    };

    addArgument(arg = null) {
        this.args.push( arg );
    }

    onMessage( handlerFunction ){
        this.handlerFunction = handlerFunction;
    }

    onError( errorHandlerFunction ){
        this.errorHandlerFunction = errorHandlerFunction;
    }

    connect() {

        this.stompClient = Stomp.over(function() {
            return new SockJS("http://localhost:8089/ws");
         });

        // automatic reconnect (delay in milli seconds)
        this.stompClient.reconnect_delay = 5000;
        this.stompClient.heartbeatIncoming = 4000;
        this.stompClient.heartbeatOutgoing = 4000;

        this.stompClient.connect({},  (frame) => {
            console.log('connected: ' + frame);
            this.stompClient.subscribe('/topic/graph_data',  (message)=>{
                console.log("message >>> %o", message);
                message = JSON.parse(message.body);
                this.handlerFunction( message, this.args );
            });
        });
    };

     disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected!");
    };

}