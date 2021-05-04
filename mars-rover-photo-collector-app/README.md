# nasa-excercise
This project using the API described here (https://api.nasa.gov/api.html) 

## This Spring boot app exposes the following endpoints.
- GET list of dates: /api/v1/dates
  - http://localhost:8080/api/v1/dates  
The endpoint reads a.txt file that has the following dates and returns the dates formatted. The April 31, 2018 is not in a valid format.  
02/27/17  
June 2, 2018  
Jul-13-2016  
April 31, 2018  

- GET list of rovers: /api/v1/rovers
  - http://localhost:8080/api/v1/rovers
- GET list of photos for a given date: /api/v1/rovers/{name}/photos?earth_date={earth_date}
  - http://localhost:8080/api/v1/rovers/opportunity/photos?earth_date=2018-06-02
- GET an individual photo: /api/v1/rovers/{name}/photos/{id}?img_src={img_src}
  - http://localhost:8080/api/v1/rovers/Curiosity/photos/617694?img_src=http://mars.jpl.nasa.gov/msl-raw-images/msss/01622/mcam/1622MR0083260010801245I01_DXXX.jpg 
  
## Testing the Spring boot app
1. Download Repo using git clone
2. Build project using Gradle  
  ./gradlew clean build
3. Start up a Local instance of the Spring boot app  
  ./gradlew bootRun
4. In a bash terminal perform curl commands using the examples above  
  curl http://localhost:8080/api/v1/rovers  