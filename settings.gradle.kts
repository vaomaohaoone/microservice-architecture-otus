rootProject.name = "microservice-architecture-otus"
include("kubernetes-base-part-2")
include("kubernetes-base-part-3")
include("prometheus-grafana")
include("hw4")
include("hw5")
include("saga")
include("saga:payment")
include("saga:stock")
include("saga:delivery")
include("saga:order")
findProject(":saga:payment")?.name = "payment"
findProject(":saga:stock")?.name = "stock"
findProject(":saga:delivery")?.name = "delivery"
findProject(":saga:order")?.name = "order"