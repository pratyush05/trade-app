# trade-app
#### A basic RESTful application written in Java using Vert.x. You can GET, PUT, POST or DELETE entities like stocks, trades and traders.

##### How to get this running:
1. Clone this repository.
2. Run `./gradlew clean build`
3. Start the application using `java -jar build/libs/pratyush-1.0-SNAPSHOT-fat.jar --conf config.json`

##### Sample example:
    Request:
    curl -X GET \
    http://localhost:9999/v1/stock \
    -H 'Accept: */*' \
    -H 'Accept-Encoding: gzip, deflate' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Length: 32' \
    -H 'Content-Type: application/json'
    
    Response:
    [
      {
        "id": 1,
        "name": "Jeff Bezos"
      },
      {
        "id": 2,
        "name": "Steve Wozniak"
      },
      {
        "id": 3,
        "name": "Mark Zuckerberg"
      },
      {
        "id": 4,
        "name": "Larry Page"
      },
      {
        "id": 5,
        "name": "Bill Gates"
      }
    ]
    
    
