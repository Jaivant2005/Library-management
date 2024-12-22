document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting the default way

    // Get the username and password values
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Validate input (you can add more validations as needed)
    if (!username || !password) {
        alert('Please fill in all fields.');
        return;
    }

    // Create an object to send to the server
    const loginData = {
        username: username,
        password: password
    };

    // Send a POST request to the server for authentication
    fetch('AdminLogin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Login failed! Please check your username and password.');
        }
        return response.json(); // Assuming the server returns a JSON response
    })
    .then(data => {
        // Handle successful login
        // You can redirect the user or show a success message
        alert('Login successful!');
        // For example, redirect to the admin dashboard
        window.location.href = 'Adminportal.html'; // Update to your dashboard URL
    })
    .catch(error => {
        // Handle errors (e.g., invalid credentials)
        alert(error.message);
    });
});
