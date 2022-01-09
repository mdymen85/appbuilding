# Domain Driven Design

I want to share a little code about some develop that I did, applying concepts of DDD **Domain Driven Design**. My focus was to construct an aggregate root class called Building, that controls all the flow below them, as the following diagram:

![](https://github.com/mdymen85/appbuilding/blob/main/appbuilding.drawio.png)

Here, as I explained in the first paragraph, all the changes must happend through the aggregate root class; so in that case, we can have a consistent hierarchy, without any danger of uncontrolled change.

## Model

I used **JPA** to model the domain. As Hibernate suggested in the best practices of JPA because of its efficient way of generating sql, I based all the relationships in bidirectional ways. 

There is some attention point when i want to save a floor passing as parameter an uuid from a building. Because of the bidirectional relationship, hibernate load all floors of that building before saveing the new floor; that will be catastrophic speaking about performance. So i had an idea about not to force a bidirectional relantionship when i wanted just to save and not to load elements. This made me think about the architecture pattern CQRS **Command Query Responsability Segregation**, in order to uste differente objects to write and to read. I will speak about this in the next episode.

## Exception

I mostly treated the exception inside de **Domain** because i applyied DDD. I use a **Controller Advice** class, called **InformationControllerAdvice.java** where i defined all the exceptions in the application, wrapping the response in new objects with some interesting information as error code, http error code, and a message error loaded from a specific file.

## Endpoints

As i mentioned before, i tried to mantain a data consistency, so all of the entities in the application depends on their aggregate root entity. Nothing can change from outside this **aggregate root**.

## Test

I developed some tests using **Rest Assured** to guaranteed the APIs from the beginning to the response of the endpoint.

## Curls

Im shearing some curls to test the application

### Building

```
curl --location --request POST 'localhost:8080/api/v1/building' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Classic",
    "address": "Rua 10 de Setembro",
    "floors": [
        {
            "number": 1,
            "apartments": [
                {
                    "number": 11            
                },
                {
                    "number": 12            
                },
                {
                    "number": 13            
                }
                ,{
                    "number": 14            
                }
            ]
        },
         {
            "number": 2,
            "apartments": [
                {
                    "number": 21            
                },
                {
                    "number": 22            
                },
                {
                    "number": 23            
                }
                ,{
                    "number": 24            
                }
            ]
        },
        {
            "number": 3,
            "apartments": [
                {
                    "number": 31            
                },           
                {
                    "number": 32            
                },
                {
                    "number": 33            
                }
                ,{
                    "number": 34            
                }
            ]
        }
    ]
}'
```

```
curl --location --request GET 'localhost:8080/api/v1/building/{{uuid}}'
```

### Floor

```
curl --location --request POST 'localhost:8080/api/v1/building/{{uuid}}/floor' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": 4,
    "apartments": [
        {
            "number": 41         
        },
        {
            "number": 42            
        },
        {
            "number": 43            
        }
        ,{
            "number": 44            
        }
    ]
}'
```

```
curl --location --request GET 'localhost:8080/api/v1/building/{{uuid}}/floor/4'
```

### Aparments

```
curl --location --request POST 'localhost:8080/api/v1/building/{{uuid}}/floor/2/apartment' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number":2
}'
```
```
curl --location --request GET 'localhost:8080/api/v1/building/{{uuid}}/floor/2/apartment/1'
```

## Articles

[Martin Fowler's page](https://martinfowler.com/tags/domain%20driven%20design.html)

[DDD Community](https://www.dddcommunity.org/library/vernon_2011/)

