// script.js

// Your JavaScript code goes here

// script.js
document.getElementsByClassName("openAPage")[0].addEventListener("click", function() {
   
    if (window.location.pathname == "/index.html") {
        window.location.href = "result.html";
    }
    else if (window.location.pathname == "/result.html") {
        window.location.href = "index.html";
    }
});

document.getElementById("openSchedule").addEventListener("click", function() {
    window.location.href = "index.html";
});

document.getElementById("openRotation").addEventListener("click", function() {
    window.location.href = "result.html";
});

document.getElementById("callApiButton").addEventListener("click", function() {
    
    fetch("/api/result", {
        method: "POST",
    })
    .then(response => response.text())
    .then(data => {
        
        console.log("working");
        document.getElementById("apiResponse").innerText = data;
    })
    .catch(error => {
        console.error("Error:", error);
    });
});
