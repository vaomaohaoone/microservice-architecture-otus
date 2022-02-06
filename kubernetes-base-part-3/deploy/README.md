### Сборка образа
    cd image
    docker build -f Dockerfile --build-arg JAR_FILE=kubernetes-base-part-3-stable.jar -t vaomaohao/kubernetes-base-part-3:stable .
    docker push vaomaohao/kubernetes-base-part-3:stable
### Установка postgresql
    helm repo add bitnami https://charts.bitnami.com/bitnami
    helm install -f helm-postgres/values.yaml hw-2 bitnami/postgresql -n otus-homework --create-namespace
### Деплой приложения
    helm install otus-hw2 ./otus-hw2 --namespace=otus-homework
### Коллекция postman для тестов
    https://www.getpostman.com/collections/a1c3483f17557385ac84