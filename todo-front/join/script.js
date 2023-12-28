document.getElementById('joinForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Get the input values
    const newUsername = document.getElementById('newUsername').value;
    const newPassword = document.getElementById('newPassword').value;
  
    // Perform basic validation (replace this with server-side validation in a real application)
    if (newUsername.trim() !== '' && newPassword.trim() !== '') {

      makePostRequest(newUsername, newPassword);

      // window.location.href = '../todo/todo.html';

      document.getElementById('joinMessage').textContent = 'Registration successful!';
      // Perform registration logic here (e.g., store the new credentials)
    } else {
      document.getElementById('joinMessage').textContent = 'Please enter both username and password.';
    }
  });

  async function makePostRequest(userId, userPW) {
    try {
      await fetch("https://todo-api.ngrok.app/api/join", {
        method: "POST",
        body: JSON.stringify({
          _id: userId,
          _pw: userPW,
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      });  
    } catch (error) {
      throw new Error('There was an error with the POST request: ' + error.message);
    }
  }
  