import React, {useEffect} from "react";
import Dygraph from 'dygraphs';




export default (props=null)=>{

    var dygraphCanvas = null;
    var motorSpeedDygraph = null;
    dygraphCanvas = null;
    dygraphCanvas = React.createRef();

    useEffect(()=>{

        console.log(">> chart component updated with new data.. %o", props.data);

      if (props.data.length === 0 || dygraphCanvas === null ) {
        return;
      }

        motorSpeedDygraph = new Dygraph(
        document.getElementById("speed_dygraph"),
        props.data,
        {
          // title: props.name,
          drawPoints: true,
          showRoller: false,
          rollPeriod: 1,
          errorBars: false,
          valueRange: props.value_range,
          labels: props.labels,
          showRangeSelector: true,
          rangeSelectorPlotFillGradientColor: "blue",
          rangeSelectorPlotStrokeColor: "rgb(2, 44, 112)",
          ylabel: props.y,
          xlabel: props.x,

        });


      window.graphInterval = setInterval(function() {

        //limit the max number of data points
        if (props.data.length > props.max_data_points) {
          props.data.splice(0, (props.data.length - props.max_data_points));
        }

        motorSpeedDygraph.updateOptions( { 'file': props.data } );

      }, 1000);

      return ()=> {

        clearInterval(window.graphInterval);

    }

    }, [props.data])

      //   #speed_dygraph .dygraph-ylabel {
      //     transform: rotate(270deg);
      // }



    return(

        <div>

            <div className="card mt-2">
              <div className="card-body p-0" id="dual_x_div" style={{width:"60%", height:"auto"}}>
              {/* <style>.dygraph-legend { text-align: right; background: none; }</style> */}

                <div ref={dygraphCanvas} id="speed_dygraph" style={{position: "relative", inset: "150px, 10px 10px 200px", width: "auto"}}></div>
              </div>
              <div className="card-footer">{props.name}</div>

            </div>

        </div>


    )


}