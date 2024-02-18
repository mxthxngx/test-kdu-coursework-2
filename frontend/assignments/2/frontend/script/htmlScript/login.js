/**
 * Logs in the user using the provided username and password.
 *
 * @param {void} 
 * @return {void} 
 */
function login() {
    let username = document.getElementsByClassName('username-input')[0].value;
    let password = document.getElementsByClassName('password-inpt')[0].value;

    if (username.trim() === '' || password.trim() === '') {
        alert('Please enter both username and password');
        return; 
    }

    let data = {
        username: username,
        password: password
    };

    fetch(`http://${window.location.hostname}:3000/api/user/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = 'index.html?username=' + encodeURIComponent(username);
        } else {
            alert('Invalid username or password');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
