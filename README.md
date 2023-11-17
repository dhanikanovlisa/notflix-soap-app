# SOAP Service for Notflix App
This is a SOAP Service for Notflix which manages users subscription and likes in Notflix app, manage users film request in Notflix premium app

## Database Schemes
![image](screenshots/notflix_soap_scheme.png)

## Endpoint API
### 1. Subscription 
| Feature             | Endpoint         |
|---------------------|--------------|
| **Get All Subscription**           | /ws/subscription/getAllSubscription?wsdl   |
| **Check Subscription Status**    |   /ws/subscription/checkSubscripitionStatus?wsdl   |
| **Get Subscripiton by Status**    | /ws/subscription/getSubscriptionByStatus?wsdl     |
| **Accept Request**    | /ws/subscription/acceptRequest?wsdl     |
| **Reject Request**    | /ws/subscription/rejectRequest?wsdl     |
| **Create Request**    | /ws/subscription/request?wsdl     |

### 2. Request Film 
| Feature             | Endpoint         |
|---------------------|--------------|
| **Get All Request Film**           | /ws/requestFilm/getAllRequestFilm?wsdl   |
| **Get Request Film By Id**    |  /ws/requestFilm/getAllRequestFilmById?wsdl   |
| **Get Request Film by Film Id**    | /ws/requestFilm/getAllRequestFilmByFilmId?wsdl     |
| **Create Request Film**    | /ws/requestFilm/createRequestFilm?wsdl     |
| **Accept Request Film**    | /ws/requestFilm/acceptRequestFilm?wsdl     |
| **Reject Request Film**    | /ws/requestFilm/rejectRequestFilm?wsdl     |

### 3. Like Film
| Feature             | Endpoint         |
|---------------------|--------------|
| **Check if user like film**           | /ws/likes/isUserLikeFilm?wsdl   |
| **Add Likes**           | /ws/likes/addLikes?wsdl   |
| **Delete Likes**           | /ws/likes/deleteLikes?wsdl   |

## Prerequisites
1. Make sure your machine has Docker and Docker Compose installed.
2. Make sure your machine has NodeJS installed.


## How To Run
1. Make an .env file based of .env.example
2. Navigate to the root directory of the project.
3. Run the following command:

    ```
    docker-compose up -d
    ```
4. Or you can run it locally
    
        ```
        mvn clean install
        ```

This will start the Notflix REST Service application using Docker containers.


## Task Division
| Feature             | NIM          |
|---------------------|--------------|
| **Database**           | 13521165     |
| **Subscription Service**    | 13521165     |
| **Request Film Service**    | 13521132     |
| **Like Film Service**    | 13521130     |
| **Email**    | 13521130     |
