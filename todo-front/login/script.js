document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Get the input values
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
    // Perform basic validation (replace this with server-side validation in a real application)
    if (username === 'username' && password === 'password') {
      document.getElementById('loginMessage').textContent = 'Login successful!';
      // Redirect to another page or perform necessary actions upon successful login
    } else {
      document.getElementById('loginMessage').textContent = 'Invalid username or password. Please try again.';
    }
  });
  