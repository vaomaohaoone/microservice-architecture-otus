### Развертывание nginx-ingress-controller
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/cloud/deploy.yaml
### Развертывание kubernetes-dashboard
    cd dashboard
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.4.0/aio/deploy/recommended.yaml
    kubectl apply -f account-role.yaml
    kubectl apply -f ingress.yaml
### Сборка и публикация образа
    cd image
    docker build -f Dockerfile --build-arg JAR_FILE=kubernetes-base-part-2-final.jar -t vaomaohao/kubernetes-base-part-2:final .
    docker push vaomaohao/kubernetes-base-part-2:final
### Развертывание сервиса:
    cd manifest
    kubectl apply -f manifest.yaml
### Проверка работоспособности:
    1) GET -x http://arch.homework/health
    response: 
    {
        "status": "UP",
        "groups": [
            "liveness",
            "readiness"
        ]
    }
    2*) GET -x http://arch.homework/otusapp/kirill/health
    response: 
    {
        "status": "UP",
        "groups": [
            "liveness",
            "readiness"
        ]
    }
### Примичание:
    Для корректной работы Ingress в среде Docker Desktop Mac OS, необходимо добавить ingress host в файл /etc/hosts
