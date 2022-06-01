# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
from locust import HttpUser, task, constant_pacing


class QuickstartUser(HttpUser):
    wait_time = constant_pacing(1)

    @task
    def callUser(self):
        self.client.get("/user")
