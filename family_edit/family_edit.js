import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayFamily();
});

function fetchAndDisplayFamily() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                    setTextNode(key, value);
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/families/' + getParameterByName('family'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayFamily();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/families/' + getParameterByName('family'), true);

    const request = {
        'name': document.getElementById('name').value,
        'classificationYear': document.getElementById('classificationYear').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log(JSON.stringify(request));
    xhttp.send(JSON.stringify(request));
}