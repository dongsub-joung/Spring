document.getElementById('loginForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Prevent the default form submission

  // Get the input values
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  let data= makePostRequest(username, password);
  console.log(data);

  // Perform basic validation (replace this with server-side validation in a real application)
  if (username === 'username' && password === 'password') {
    document.getElementById('loginMessage').textContent = 'Login successful!';
    // Redirect to another page or perform necessary actions upon successful login
  } else {
    document.getElementById('loginMessage').textContent = 'Invalid username or password. Please try again.';
  }
});


async function makePostRequest(userId, userPW) {
  try {
    await fetch("https://localhost:9090/api/login", {
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