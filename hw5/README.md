## ДЗ №5 
### Apigateway  
#### Запуск приложения:  
helm install hw5 ./deploy/helm/hw5 --namespace=otus-homework  
#### Запуск тестов postman коллекции  
newman run ./collection/Hw5.postman_collection.json  
#### Примичание:  
Авторизация jwt-токен, nginx api-gateway  