import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySpecies();
});

function fetchAndDisplaySpecies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    if(getParameterByName('family')) {
        xhttp.open("GET", getBackendUrl() + '/api/families/' + getParameterByName('family') + '/species/' + getParameterByName('species'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/species/' + getParameterByName('species'))
    }
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySpecies();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/families/' + getParameterByName('family') + '/species/'
        + getParameterByName('species'), true);

    const request = {
        'name': document.getElementById('name').value,
        'isHallucinogenic': document.getElementById('isHallucinogenic').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}