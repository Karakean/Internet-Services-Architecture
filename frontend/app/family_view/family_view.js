import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    setLinkNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFamily();
    fetchAndDisplaySpecies();
});

function fetchAndDisplaySpecies() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySpecies(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/families/' + getParameterByName('family') + '/species', true);
    xhttp.send();
}

function displaySpecies(species) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    species.species.forEach(species => {
        tableBody.appendChild(createTableRow(species));
    })
}

function createTableRow(species) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(species.name));
    tr.appendChild(createLinkCell('view', '../family_view/family_view.html?family=' + family["id"]));
    tr.appendChild(createLinkCell('edit', '../family_edit/family_edit.html?family=' + family["id"]));
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
    xhttp.open("DELETE", getBackendUrl() + '/api/families/' + getParameterByName('family') + '/species/' + species.id, true);
    xhttp.send();
}

function fetchAndDisplayFamily() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFamily(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/families/' + getParameterByName('family'), true);
    xhttp.send();
}

function displayFamily(family) {
	setTextNode('id', family.id);
    setTextNode('name', family.name);
    setTextNode('classificationYear', family.classificationYear);

    setLinkNode('add', 'ADD SPECIES', "../species_add/species_add.html?family=" + getParameterByName('family'));
}
