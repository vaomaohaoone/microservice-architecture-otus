# Steps  

## Upgrade nginx-ingress-controller  
helm upgrade --install ingress-nginx ingress-nginx \  
--repo https://kubernetes.github.io/ingress-nginx \  
--namespace ingress-nginx --create-namespace \  
--set controller.metrics.enabled=true \  
--set-string controller.podAnnotations."prometheus\.io/scrape"="true" \  
--set-string controller.podAnnotations."prometheus\.io/port"="10254" \  
--set controller.metrics.serviceMonitor.enabled=true  

## Install prometheus-stack  
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts  
helm repo update  
helm install -f deploy/prometheus-stack/values.yaml stable-prometheus-stack prometheus-community/kube-prometheus-stack  
kubectl apply -f deploy/grafana/grafana-ingress.yaml -n otus-homework  
kubectl apply -f deploy/prometheus/prometheus-ingress.yaml -n otus-homework  

## Install app  
helm install otus-hw3 ./deploy/otus-hw3 --namespace=otus-homework

## Run locust  
locust --host=http://arch.homework/

## Boards  
![latency_with_quantilies](./static/latency_with_quantiles.png)
![requests_per_seconds](./static/requests_per_seconds.png)
![requests_from_nginx_controller](./static/requests_from_nginx_controller.png)
![memory_usage](./static/memory_usage.png)
![cpu_usage](./static/cpu_usage.png)
![database_lock_count](./static/database_lock_count.png) 

### Задание №3
Prometheus. Grafana

#### Цель: 
В этом ДЗ вы научитесь инструментировать сервис. 

#### Описание/Пошаговая инструкция выполнения домашнего задания: 
Инструментировать сервис из прошлого задания метриками в формате Prometheus с помощью библиотеки для вашего фреймворка и ЯП.  
Сделать дашборд в Графане, в котором были бы метрики с разбивкой по API методам:  

1. Latency (response time) с квантилями по 0.5, 0.95, 0.99, max  
2. RPS  
3. Error Rate - количество 500ых ответов Добавить в дашборд графики с метрикам в целом по сервису, взятые с nginx-ingress-controller:  
4. Error Rate - количество 500ых ответов Настроить алертинг в графане на Error Rate и Latency. На выходе должно быть:  
скриншоты дашборды с графиками в момент стресс-тестирования сервиса. Например, после 5-10 минут нагрузки. json-дашборды.  
5. Задание со звездочкой (+5 баллов) Используя существующие системные метрики из кубернетеса, добавить на дашборд графики с метриками:  
Потребление подами приложения памяти  
Потребление подами приолжения CPU  
Инструментировать базу данных с помощью экспортера для prometheus для этой БД.  
Добавить в общий дашборд графики с метриками работы БД. 