import { apiClient } from "./apiClient";

export function retrieveAllTodosForUsernameApi(username) {
  return apiClient.get(`/users/${username}/todos`);
}

export function deleteTodoApi(username, id) {
  return apiClient.delete(`/users/${username}/todos/${id}`);
}

export function retrieveTodoApi(username, id) {
  return apiClient.get(`/users/${username}/todos/${id}`);
}

export function updatedTodoApi(username, id, todo) {
  return apiClient.put(`/users/${username}/todos/${id}`, todo);
}

export function createTodoApi(username, id, todo) {
  return apiClient.post(`/users/${username}/todos`, todo);
}
