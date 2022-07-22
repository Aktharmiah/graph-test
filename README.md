# README

This is a simple application, comprising of a frontend, created using HTML and Javascript and the backend, created using Java and SpringBoot.<br />
Its aim is to transmit simple graph data via a websocket so that it can be displayed on the frontend as live data.


## The problem

We are aware of a number of problems with this application which must be fixed. The known problems are as follows:

1) The graph data is sent in the order that it was created in.
2) When the end of the list is reached, the websocket continues to reach into the data collection.
3) Cross-origin request are being blocked.
4) Syntax errors.

Note - there may be other problems which are not noted here.

## Your task
1) Create a sorting algorithm so that the data is displayed in chronological order. This can be as part of the backe-end or the front-end. The data is 
   stored in the following format: [{epochTime, floatValue}, {epochTime, floatValue}, {epochTime, floatValue}]
   
       //Sorting graphData List
       Collections.sort(graphData, new Comparator<Object[]>() {

		@Override
		public int compare(Object[] o1, Object[] o2) {
			int c=0;
			for (int i = 0; i < Math.min(o1.length, o2.length); i++) {
				c = (Integer) o2[0]- (Integer)o1[0];
				if(c==0) {
					double timeValue=  (Double) o2[1]- (Double)o1[1];
					c= (int) timeValue;
				}
				if(c!=0) return c;
			}
			return 0;
		}
    	 }); 
       
    }
   
2) Offer a solution to the item #2 as above
4) Cross-origin settings needs correcting. They can be found in the `WebSocketConfig` file.
5) Correct syntax errors.
6) Review the code and make recommendations using comments (`//, #, /** */`)
7) Make any improvements that you feel would make the application work better.

Output:



## Assumptions
1) You have JVM installed on your machine (>=v11)
2) You are running Gradle
3) You are running NodeJs and are familiar with the basics of `npm`
4) Port 8089 and 3000 are available on your machine.
5) You are running this application on a Linux machine or POSIX system


## Running the application
1) `cd` into `src/frontent` directory and run `npm i`
2) From the directory contining your `build.gradle` file, run `./gradlew bootRun` to start the websocket server
3) Run `npm start`. This will launch the application on your browser on port `3000`

### Suggetions

- Use browsers built-in dev-tools to assess error messages and monitor network traffic.
- Use you backends debug output to assess errors.
