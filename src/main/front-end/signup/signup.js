function signUp(username, password) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/users", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        "username": username,
        "password": password
    }));
}

function checkUsername(username) {
    return fetch("http://localhost:8080/users/check/" + username)
        .then(response => response.json())
        .then(data => data);
}

// takes username and returns userId. Returns undefined if username not in database.
async function getUserid(username) {
    return fetch("http://localhost:8080/users/" + username)
        .then(response => response.json())
        .then(user => user.id)
        .catch(reject => console.log(reject));
}

// if username not in database, allow to create an account with username. Otherwise, alert
async function createAccount(){
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const usernameInDB = await checkUsername(username);


    if (usernameInDB) {
        alert("Username is already in use. Please enter a different username.");
    } else {
        await signUp(username, password);
        let userid = await getUserid(username);
        localStorage.setItem("userid", userid.toString());
        location.href = "../start-when/start-when.html";
    }
}






