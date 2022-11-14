#GCP-Project
Simple application for Google Cloud Platform, originially created by [lehauchicha](https://dev.to/lehauchicha/google-cloud-platform-deploy-simple-java-spring-boot-application-4f85) but I had to do some changes to get it to work on my environment.

## Technologies
This project uses:
1. Springboot
2. Mapstruct
3. Docker 

## TODO
1. use 

## Building the docker image 
```
docker build -t gcp-project:0.0.1 .
```


## running on local docker container
I'll use my postgres instance of the kong DB.
```
docker run -p8083:8080 -e DB_NAME=postgres -e DB_USER=kong --network=kong-net --env DB_HOST=kong-database -e DB_PASS=kongpass gcp-project:0.0.1
```

## Testing

### Create a new post request
```
curl -v -X POST -d '{"title":"Welcome","description":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned","fullText":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned", "author":"drakool the hulk"}' -H 'Content-Type: application/json' -H 'Accept: application/json' http://localhost:8083/posts
```

### Create a new post response

```
* upload completely sent off: 415 out of 415 bytes
< HTTP/1.1 201
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 13 Nov 2022 18:38:50 GMT
<
* Connection #0 to host localhost left intact
{"id":1,"title":"Welcome","description":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned","fullText":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned","author":"drakool the hulk","temp":null}* Closing connection 0
```

### Get Post by ID request
```
 curl -v http://localhost:8083/posts/1
```


### Get Post By ID response
```
 curl -v http://localhost:8083/posts/1                                                                                       ─╯
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8083 (#0)
> GET /posts/1 HTTP/1.1
> Host: localhost:8083
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 13 Nov 2022 18:43:04 GMT
<
* Connection #0 to host localhost left intact
{"id":1,"title":"Welcome","description":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned","fullText":"The content of the post is here. I hope that the length is more than 50 characters otherwise it will not be processed successfully and 400 response needs to be returned","author":"drakool the hulk","temp":null}* Closing connection 0
```