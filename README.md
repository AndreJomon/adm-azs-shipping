# CRUD of Frete
This project was create to show a simple CRUD of Fretes. With all basics operations.

# Framework

This project was built using Spring Boot and MongoDB.

## Instalation
With docker installed, use `docker-compose up` on the root of this project.

## API Documentation
### Populate database
```http 
GET http://localhost:8080/api/v1/frete/populate
```
Populate the database to use another functions easily.

### Insertion
```http
POST http://localhost:8080/api/v1/frete/insert
```
Insert a new value in the database using a key-value of strings with any desired value in the body of the request.
Ex:
```
{"peso": "10", "triagem": "10", "tempo": "40 dias"}
```
This will create a document with these values on the database:
```JSON
{
  "_id":"X",
  "customProperties": [
    {
      "name" : "peso",
      "value" : "10"
    },
    {
      "name" : "triagem",
      "value": "10"
    },
    {
      "name" : "tempo",
      "value": "40 dias"
    }
  ]
}
```

### Search
```http
GET http://localhost:8080/api/v1/frete/search/{page}
```
A generic search to get all the files in the database, the result is pageable with five resulter per page, starting in the page 0. The return is a page object with information about the pagination. The results of the search are in the content attribute.

```http
GET http://localhost:8080/api/v1/frete/search/{searchable_term}/{page}
```
A search for a specific term. This will return all the documents with this term inside some value field of the custom properties added in the insert step.  The return is a page object with information about the pagination. The results of the search are in the content attribute.

### Update
```http
POST http://localhost:8080/api/v1/frete/update
```
Update a existing document on the database. The body of the request need to have this structure:

```JSON
{
  "id" : "{id found on search}",
  "freteInfo" : {
      "tempo" : "20 dias"
    }
}
```
The document with this id will be updated with the information of the key-values of "freteInfo".

### Delete
```http
POST http://localhost:8080/api/v1/frete/delete
```
Delete a certain document. The body of the request need to have this structure:
```
{id found on search}
```
The document of this id will be completely removed.
