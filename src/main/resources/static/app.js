const form = document.getElementById('todo-form');
const todoList = document.getElementById('todo-list');
const titleInput = document.getElementById('title');
const descriptionInput = document.getElementById('description');

const API_URL = '/api/todos';

window.onload = fetchTodos;

form.addEventListener('submit', function (e) {
  e.preventDefault();
  const title = titleInput.value.trim();
  const description = descriptionInput.value.trim();

  if (!title || !description) return;

  fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, description, completed: false })
  })
    .then(response => response.json())
    .then(() => {
      form.reset();
      fetchTodos();
    });
});

function fetchTodos() {
  fetch(API_URL)
    .then(res => res.json())
    .then(todos => {
      todoList.innerHTML = '';
      todos.forEach(todo => {
        const li = document.createElement('li');
        li.innerHTML = `
          <div>
            <input type="checkbox" ${todo.completed ? 'checked' : ''} onchange="toggleCompleted(${todo.id}, this.checked)">
            <strong>${todo.title}</strong> - ${todo.description}
          </div>
          <button onclick="deleteTodo(${todo.id})">🗑️</button>
        `;
        todoList.appendChild(li);
      });
    });
}

function toggleCompleted(id, completed) {
  fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ completed })
  }).then(fetchTodos);
}

function deleteTodo(id) {
  fetch(`${API_URL}/${id}`, {
    method: 'DELETE'
  }).then(fetchTodos);
}
