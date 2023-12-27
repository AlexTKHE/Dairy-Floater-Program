// script.js

// Your JavaScript code goes here

// script.js


document.getElementById("openSchedule").addEventListener("click", function() {
    window.location.href = "index.html";
});

document.getElementById("openRotation").addEventListener("click", function() {
    window.location.href = "result.html";
});


document.addEventListener('DOMContentLoaded', function () {
    const callApiButton = document.getElementById('callApiButton');
    const apiResponseContainer = document.getElementById('apiResponse');

    callApiButton.addEventListener('click', function () {
        // Replace with your API endpoint
        const apiUrl = '/api/message';

        // Customize headers if needed (e.g., for authentication)
        const headers = {
            'Content-Type': 'application/json' // Adjust based on the API requirements
        };

        fetch(apiUrl, { method: 'POST', headers: headers })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.text();
            })
            .then(data => {
                console.log('API Response:', data);
                // Update the content on the page with the API response
                apiResponseContainer.innerHTML = `API Response: ${data}`;
            })
            .catch(error => {
                console.error('API Error:', error);
                // Handle errors, e.g., display an error message to the user
            });
    });
});

document.getElementById("callApiWithNumber").addEventListener("click", function () {
    // Replace with your API endpoint
    const apiUrl = '/number/employees';

    // Get the input value
    const numInputValue = document.getElementById('numInput').value;

    // Customize headers if needed (e.g., for authentication)
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    // Prepare the data to send to the backend
    const data = `n=${numInputValue}`;

    fetch(apiUrl, {
        method: 'POST',
        headers: headers,
        body: data
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.text();
    })
    .then(data => {
        console.log('API Response:', data);
        // Update the content on the page with the API response
        document.getElementById('apiResponse').innerHTML = `API Response: ${data}`;
    })
    .catch(error => {
        console.error('API Error:', error);
        // Handle errors, e.g., display an error message to the user
    });
});
