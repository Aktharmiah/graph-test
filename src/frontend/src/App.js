import './App.css';
import React, { useEffect, useState } from 'react';
import SpeedChart from './chart_component';
import MyWebsocketClient from './websocket_client';
import $ from 'jquery';

var mySocket = new MyWebsocketClient( "action" );

function App() {

    var graphData, setGraphData;
    [graphData, setGraphData] = useState([]);

    useEffect(() => {

        mySocket.onMessage((jsonMessage, args)=>{
            console.log("Handler function executed!!!");

            jsonMessage[0] = new Date(jsonMessage[0]);

            console.log("Message with body received.. %o", jsonMessage);

            setGraphData( [...graphData, jsonMessage] );

        });

        mySocket.onError((errorMessage, args)=>{

            console.log("Error handler function executed!!!");
            console.log("Error message: %o", errorMessage);

        })


        //cleanup function
        return ()=>{

            clearInterval( window.intervalId );
        }

    }, [graphData])


  return (
        <div>
            <h2 style={{display: 'flex', justifyContent: 'center'}}>WebSocket connection:</h2>
            <h4 style={{display: 'flex', justifyContent: 'center'}} id="conversation">Responses..</h4>

            <table style={{display: 'flex', justifyContent: 'center'}}>
               <tbody id="incoming_messages"></tbody>
            </table>

            <SpeedChart style={{display: 'flex', justifyContent: 'center'}} max_data_points={50} name={"Speed Chart"} labels={['Time', 'Speed: ']} value_range={[0.0, 100]} data={graphData}/>

          </div>

  );
}

export default App;

// old code

