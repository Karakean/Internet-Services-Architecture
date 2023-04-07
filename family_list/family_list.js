import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFamilies();
});

function fetchAndDisplayFamilies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFamilies(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/families', true);
    xhttp.send();
}

function displayFamilies(families) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    families.families.forEach(family => {
        tableBody.appendChild(createTableRow(family));
    })
}

function createTableRow(family) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(family["id"]));
    tr.appendChild(createTextCell(family["name"]));
    tr.appendChild(createLinkCell('view', '../family_view/family_view.html?family=' + family["id"]));
    tr.appendChild(createLinkCell('edit', '../family_edit/family_edit.html?family=' + family["id"]));
    tr.appendChild(createButtonCell('delete', () => deleteFamily(family)));
    return tr;
}

function deleteFamily(family) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayFamilies();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/families/' + family["id"], true);
    xhttp.send();
}
