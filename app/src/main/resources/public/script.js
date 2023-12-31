// COOKIES

function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        while (cookie.charAt(0) == ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        if (cookie.indexOf(nameEQ) == 0) {
            return cookie.substring(nameEQ.length, cookie.length);
        }
    }
    return null;
}

// Function to delete a cookie by name
function eraseCookie(name) {
    document.cookie = name + '=; Max-Age=-99999999;';
}

// END OF COOKIES





function validateNumericInput(event) {
    const allowedKeys = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'Backspace'];

    // Check if the pressed key is in the allowedKeys array
    if (!allowedKeys.includes(event.key)) {
        // Prevent the input if the key is not allowed
        event.preventDefault();
    }
}

function showHiddenButton() {
    var hiddenButton = document.getElementById('hiddenButton');
    hiddenButton.style.display = 'inline-block';
}

function hideDisclaimer() {
    document.getElementById('disclaimer').style.display = 'none';
}

function showInstructions() {
    document.getElementById('instructions').style.display = 'inline-block';
    document.getElementById('resetButton').style.display = 'inline-block';
}

function createSchedule() {
    const apiUrl = '/number/employees';
    const numInputValue = document.getElementById('numInput').value;
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
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
            const myArray = data.split('.');
            let spacedArray1 = [];
            let spacedArray2 = [];
            let spacedArray3 = [];
            let x = 0;
            let c = myArray.length

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
            if (c - Math.trunc(c / 3) * 3 == 0) {
                spacedArray1[spacedArray1.length - 1] += ".";
                spacedArray2[spacedArray2.length - 1] += ".";

            } else if (c - Math.trunc(c / 3) * 3 == 1) {
                spacedArray3[spacedArray3.length - 1] += ".";
                spacedArray2[spacedArray2.length - 1] += ".";

            } else {
                spacedArray1[spacedArray1.length - 1] += ".";
                spacedArray3[spacedArray3.length - 1] += ".";

            }
            spacedArray1[spacedArray1.length - 1] += "<br>";
            spacedArray2[spacedArray2.length - 1] += "<br>";

            let finalText1 = spacedArray1.join(".<br>") + "<br> ";
            let finalText2 = spacedArray2.join(".<br>") + "<br> ";
            let finalText3 = spacedArray3.join(".<br>") + "<br> ";

            


            // if (finalText1.substring(finalText1.length - 5).includes("<br>")) {
            //     finalText1 = finalText1.substring(0, finalText1.length - 5) + finalText1.substring(finalText1.length - 5, finalText1.lastIndexOf("."));

            // }
            // if (finalText1.substring(finalText1.length - 5).includes("<br>")) {
            //     finalText1 = finalText1.substring(0, finalText1.length - 5) + finalText1.substring(finalText1.length - 5, finalText1.lastIndexOf("."));

            // }
            // if (finalText2.substring(finalText2.length - 5).includes("<br>")) {
            //     finalText2 = finalText2.substring(0, finalText2.length - 5) + finalText2.substring(finalText2.length - 5, finalText2.lastIndexOf("."));
            // }
            // if (finalText2.substring(finalText2.length - 5).includes("<br>")) {
            //     finalText2 = finalText2.substring(0, finalText2.length - 5) + finalText2.substring(finalText2.length - 5, finalText2.lastIndexOf("."));
            // }
            // finalText1 = finalText1.trim();
            // if (finalText1.charAt(finalText1.length - 1) !== ".") {
            //     finalText1 += ".";
            // }


           

            setCookie('schedule1', finalText1, 1);
            setCookie('schedule2', finalText2, 1);
            setCookie('schedule3', finalText3, 1);

            document.getElementById('apiResponse1').innerHTML = `${finalText1}`;
            document.getElementById('apiResponse2').innerHTML = `${finalText2}`;
            document.getElementById('apiResponse3').innerHTML = `${finalText3}`;
        })
        .catch(error => {
            console.error('API Error:', error);
        });
}

function setApiResponseOnes() {
    const apiResponseContent = apiResponse1.innerHTML;
    setCookie('schedule1', apiResponseContent, 1);
}

function setApiResponseTwos() {
    const apiResponseContent = apiResponse2.innerHTML;
    setCookie('schedule2', apiResponseContent, 1);
}

function setApiResponseThrees() {
    const apiResponseContent = apiResponse3.innerHTML;
    setCookie('schedule3', apiResponseContent, 1);
}

function createRotations() {
    const apiUrl = '/api/createRotations';
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
    const schedule = [
        getCookie('schedule1'),
        getCookie('schedule2'),
        getCookie('schedule3')
    ].join('');    
    const lineInputValue = document.getElementById('lineInput').value;
    const startInputValue = document.getElementById('startInput').value;
    const endInputValue = document.getElementById('endInput').value;

    let cashiersInputValue = lineInputValue;
    let orderTakersInputValue = lineInputValue;

    if (document.getElementById('cashiersInput').value !== null) {
        cashiersInputValue = document.getElementById('cashiersInput').value;
    }

    if (document.getElementById('orderTakersInput').value !== null) {
        orderTakersInputValue = document.getElementById('orderTakersInput').value;
    }

    // const data = `n=${schedule}~${lineInputValue}~${startInputValue}~${endInputValue}~${cashiersInputValue}~${orderTakersInputValue}`;
    // const data = `n=${schedule}` + `~${lineInputValue}` + `~${startInputValue}`+ `~${endInputValue}` + `~${cashiersInputValue}` + `~${orderTakersInputValue}`;
    const data = new FormData();
    data.append('schedule', schedule);
    data.append('lineInput', lineInputValue);
    data.append('startInput', startInputValue);
    data.append('endInput', endInputValue);
    data.append('cashiersInput', cashiersInputValue);
    data.append('orderTakersInput', orderTakersInputValue);

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
        .then(result => {
            document.getElementById('rotations1').innerHTML = result;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function callApiWithNumber() {
    createSchedule();
    showHiddenButton();
    hideDisclaimer();
    showInstructions();
}

function openRotations() {
    window.location.href = "result.html";
}

function isCookieTrue(cookieName) {
    const cookieValue = getCookie(cookieName);
    return cookieValue === 'true';
}

function resetCookiesAndReload() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    location.reload();
}

function createRotationsClick() {
    createRotations();
}



// event listners



document.getElementById("openSchedule").addEventListener("click", function () {
    window.location.href = "index.html";
});

document.getElementById("openRotation").addEventListener("click", openRotations);

document.getElementById("callApiWithNumber").addEventListener("click", callApiWithNumber);

document.getElementById("numInput").addEventListener("keyup", function (event) {
    if (event.key === "Enter") {
        createSchedule();
        showHiddenButton();
        hideDisclaimer();
        showInstructions();
    }
});

document.addEventListener('DOMContentLoaded', function () {
    const schedule1 = getCookie('schedule1');
    const schedule2 = getCookie('schedule2');
    const schedule3 = getCookie('schedule3');

    if (schedule1 != null && schedule2 != null && schedule3 != null) {
        showHiddenButton();
        hideDisclaimer();
        showInstructions();
        const defaultSchedule1 = 'Default Schedule 1';
        const defaultSchedule2 = 'Default Schedule 2';
        const defaultSchedule3 = 'Default Schedule 3';

        document.getElementById('apiResponse1').innerHTML = schedule1 || defaultSchedule1;
        document.getElementById('apiResponse2').innerHTML = schedule2 || defaultSchedule2;
        document.getElementById('apiResponse3').innerHTML = schedule3 || defaultSchedule3;
    }
});

document.getElementById("resetButton").addEventListener("click", resetCookiesAndReload);

document.getElementById

