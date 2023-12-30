function addTask() {
  const taskInput = document.getElementById('taskInput');
  const taskText = taskInput.value.trim();

  if (taskText !== '') {
    const taskList = document.getElementById('taskList');
    const newTask = document.createElement('li');
    newTask.textContent = taskText;

    saveTodo(taskText);

    // Add delete button
    const deleteButton = document.createElement('button');
    deleteButton.textContent = '❌';
    deleteButton.classList.add('delete-btn');
    deleteButton.onclick = function () {
      taskList.removeChild(newTask);
    };

    newTask.appendChild(deleteButton);
    taskList.appendChild(newTask);
    taskInput.value = '';
  }
}

let todos= loadTodos();


async function saveTodo(todoText){
  try {
    await fetch("https://localhost:9090/api/todo/add", {
      method: "POST",
      body: JSON.stringify({
        textBody: todoText,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    });  
  } catch (error) {
    throw new Error('There was an error with the POST request: ' + error.message);
  }
}

async function loadTodos(){
  try {
    await fetch("https://localhost:9090/api/todos", {
      method: "POST",
      body: JSON.stringify({
        textBody: todoText,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    });  
  } catch (error) {
    throw new Error('There was an error with the POST request: ' + error.message);
  }
}