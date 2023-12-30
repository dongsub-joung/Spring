document.getElementById('loginForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Prevent the default form submission

  // Get the input values
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  let res= makePostRequest(username, password);
  console.log(res);
  if (res.data == "Login_Successful"){
    document.getElementById('loginMessage').textContent = 'Login successful!';
    window.location.href = '../todo/todo.html';
  }else{
    document.getElementById('loginMessage').textContent = 'Invalid username or password. Please try again.';
  }
});

async function makePostRequest(userId, userPW) {
  const url = `https://localhost:9090/api/login/${userId}/${userPW}`;

  try {
    const response = await fetch(url, {
      method: "POST",
      // You might need headers or other configurations here
    });

    if (!response.ok) {
      throw new Error('Failed to make the POST request');
      // You can handle different HTTP status codes here if needed
    }

    // Handle the response data if required
    // const responseData = await response.json();
    // Do something with the responseData

  } catch (error) {
    throw new Error('There was an error with the POST request: ' + error.message);
  }
}
