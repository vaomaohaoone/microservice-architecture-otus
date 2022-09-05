### ДЗ Идемпотентность
#### Идемпотентные методы списания суммы и (опция) удаления заказа
#### Запуск: 
kubectl apply -f dockerize/ingress.yaml 
kubectl apply -f dockerize/idempotency.yaml
newman run postman/idempotency.postman_collection.json