import { apiClient } from "./apiClient";

export function retrieveHelloWorldBean() {
  return apiClient.get("http://localhost:8080/hello-world-bean");
}

export function retrieveHelloWorldPathVariable(username, token) {
  return apiClient.get(
    `http://localhost:8080/hello-world/path-variable/${username}`,
    { headers: { Authorization: token } }
  );
}