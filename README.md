# note-app

# PSQL DB

Run docker-compose up if you run locally.

# Note service
To deploy locally use docker-compose up --build -d noteservice
The bellow counted examples will use already deployed service on AWS which uses RDS on AWS.

1. To get authentication token use:
 curl -X POST \
  'http://3.16.42.110:8080/oauth/token?grant_type=password&scope=webclient&username=test2@email.com&password=password' \
  -H 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
  -H 'cache-control: no-cache'
  
  It will return JSON like this:
  {
    "access_token": "The access token",
    ...
    ...
    ...    
}

The access token will be used on other calls.

2. To create new note:
curl -X POST \
  http://3.16.42.110:8080/api/v1/notes \
  -H 'Authorization: Bearer The access token' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"userEmail": "test1@email.com",
	"title": "test note3 title",
	"note": "test note3 description"
   }'

3. To update existing note:
curl -X PUT \
  http://3.16.42.110:8080/api/v1/notes \
  -H 'Authorization: Bearer The access token' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"id": 2,
	"userEmail": "test2@email.com",
	"title": "test note2 title",
	"note": "test note2 description test note2 descriptio test note2 description"
}'

4. To delete the note:
curl -X DELETE \
  http://127.0.0.1:8080/api/v1/notes/2 \
  -H 'Authorization: Bearer The access token' \
  -H 'cache-control: no-cache'

5. To get list of notes:
curl -X GET \
  http://127.0.0.1:8080/api/v1/notes/ \
  -H 'Authorization: Bearer The access token' \
  -H 'cache-control: no-cache'
  
6. To get single note:
curl -X GET \
  http://127.0.0.1:8080/api/v1/notes/5 \
  -H 'Authorization: Bearer The access token' \
  -H 'cache-control: no-cache'

