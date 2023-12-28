document.getElementById('joinForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    
    // Get the input values
    const newUsername = document.getElementById('newUsername').value;
    const newPassword = document.getElementById('newPassword').value;
  
    // Perform basic validation (replace this with server-side validation in a real application)
    if (newUsername.trim() !== '' && newPassword.trim() !== '') {
      document.getElementById('joinMessage').textContent = 'Registration successful!';
      // Perform registration logic here (e.g., store the new credentials)
    } else {
      document.getElementById('joinMessage').textContent = 'Please enter both username and password.';
    }
  });
  