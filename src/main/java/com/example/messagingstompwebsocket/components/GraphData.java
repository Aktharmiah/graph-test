package com.example.messagingstompwebsocket.components;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GraphData {

    private ArrayList<Object[]> graphData;
    private int index = 0;

    public GraphData(){

        this.graphData = new ArrayList<>();

        this.graphData.add( new Object[] {1653581400,21.00});
        this.graphData.add( new Object[] {1653581461,2.10});
        this.graphData.add( new Object[] {1653581452,65.00});
        this.graphData.add( new Object[] {1653581431,55.00});
        this.graphData.add( new Object[] {1653581431,55.20});
        this.graphData.add( new Object[] {1653581444,42.00});
        this.graphData.add( new Object[] {1653581452,32.10});
        this.graphData.add( new Object[] {1653581431,22.09});
        this.graphData.add( new Object[] {1653581431,23.05});
        this.graphData.add( new Object[] {1653581431,33.02});
        this.graphData.add( new Object[] {1653581411,33.03});
        this.graphData.add( new Object[] {1653581431,42.21});
        this.graphData.add( new Object[] {1653581431,24.05});
        this.graphData.add( new Object[] {1653581431,24.90});
        this.graphData.add( new Object[] {1653581413,19.55});
        this.graphData.add( new Object[] {1653581431,19.65});
        this.graphData.add( new Object[] {1653581421,13.2});
        this.graphData.add( new Object[] {1653581431,11.5});
        this.graphData.add( new Object[] {1653581450,16.21});
        this.graphData.add( new Object[] {1653581431,21.1});
        this.graphData.add( new Object[] {1653581441,21.4});
        this.graphData.add( new Object[] {1653581431,19.20});
        this.graphData.add( new Object[] {1653581472,19.31});
        this.graphData.add( new Object[] {1653581431,15.54});
        this.graphData.add( new Object[] {1653581481,15.46});
        this.graphData.add( new Object[] {1653581402,16.54});
        this.graphData.add( new Object[] {1653581431,16.52});
        this.graphData.add( new Object[] {1653581463,23.33});

    }


    /**
     * Gets the data point at the index
     * @return
     */
    public Object[] getHeadObject(){

        Object[] data = this.graphData.get(this.index);
        this.index += 1;

        return data;
    }


}
