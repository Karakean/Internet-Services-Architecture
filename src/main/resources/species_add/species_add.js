import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    UpdateFamilyField();
    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            location.replace("../family_view/family_view.html?family=" + getParameterByName('family'));
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/species/')

    const request = {
        'name': document.getElementById('name').value,
        'isHallucinogenic': document.getElementById('isHallucinogenic').value,
        'familyId': document.getElementById('familyId').value
    };
    xhttp.setRequestHeader('Content-Type', 'application/json');
    console.log(JSON.stringify(request));
    xhttp.send(JSON.stringify(request));
}

function UpdateFamilyField() {
    document.getElementById('familyId').value = getParameterByName('family');
}