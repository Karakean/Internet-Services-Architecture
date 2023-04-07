import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySpecies();
});

function fetchAndDisplaySpecies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySpecies(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/species', true);
    xhttp.send();
}

function displaySpecies(species) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    species.species.forEach(oneSpecies => {
        tableBody.appendChild(createTableRow(oneSpecies));
    })
}

function createTableRow(species) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(species["name"]));
    tr.appendChild(createLinkCell('view', '../species_view/species_view.html?species=' + species["id"]));
    tr.appendChild(createLinkCell('edit', '../species_edit/species_edit.html?species=' + species["id"]));
    tr.appendChild(createButtonCell('delete', () => deleteSpecies(species)));
    return tr;
}

function deleteSpecies(species) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySpecies();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/species/' + species["id"], true);
    xhttp.send();
}
