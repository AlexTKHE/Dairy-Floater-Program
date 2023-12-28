// script.js

// Your JavaScript code goes here

// script.js

function validateNumericInput(inputElement) {
    // Get the value of the input element
    const inputValue = inputElement.value.trim();

    // Check if the input value is a valid number
    if (!/^\d+$/.test(inputValue)) {
        // If not a number, clear the input value
        inputElement.value = '';
        alert('Please enter a valid number.');
    }
}

function showHiddenButton() {
    // Show the hidden button by setting display to 'inline-block'
    var hiddenButton = document.getElementById('hiddenButton');
    hiddenButton.style.display = 'inline-block';
}


document.getElementById("openSchedule").addEventListener("click", function() {
    window.location.href = "index.html";
});

document.getElementById("openRotation").addEventListener("click", function() {
    window.location.href = "result.html";
});

function createSchedule() {
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
        // console.log('API Response:', data);
        // // Update the content on the page with the API response
        // document.getElementById('apiResponse').innerHTML = `API Response: ${data}`;
        const myArray = data.split('.');
        const spacedArray1 = [];
        const spacedArray2 = [];
        const spacedArray3 = [];
        let x = 0;

        for (i = 0; i < myArray.length; i++) {
            if (x == 0) {
                spacedArray1.push(myArray[i]);
                x++;
            }
            else if (x == 1) {
                spacedArray2.push(myArray[i]);
                x++;
            }
            else {
                spacedArray3.push(myArray[i]);
                x = 0;
            }
        }        
        document.getElementById('apiResponse1').innerHTML = `${spacedArray1.join(".<br>")}`;
        document.getElementById('apiResponse2').innerHTML = `${spacedArray2.join(".<br>")}`;
        document.getElementById('apiResponse3').innerHTML = `${spacedArray3.join(".<br>")}`;
    })
    .catch(error => {
        console.error('API Error:', error);
        // Handle errors, e.g., display an error message to the user
    });
}


document.getElementById("callApiWithNumber").addEventListener("click", function () {
    createSchedule();
    showHiddenButton();
});

document.getElementById("numInput").addEventListener("keyup", function(event) {
    if (event.key === "Enter") {
        createSchedule();
        showHiddenButton();
    }
});

function openRotations() {
        window.location.href = "result.html";
}