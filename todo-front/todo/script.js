function addTask() {
    const taskInput = document.getElementById('taskInput');
    const taskText = taskInput.value.trim();
  
    if (taskText !== '') {
      const taskList = document.getElementById('taskList');
      const newTask = document.createElement('li');
      newTask.textContent = taskText;
  
      // Add delete button
      const deleteButton = document.createElement('button');
      deleteButton.textContent = '❌';
      deleteButton.classList.add('delete-btn');
      deleteButton.onclick = function() {
        taskList.removeChild(newTask);
      };
  
      newTask.appendChild(deleteButton);
      taskList.appendChild(newTask);
      taskInput.value = '';
    }
  }
  