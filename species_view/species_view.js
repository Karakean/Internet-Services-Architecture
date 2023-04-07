import {
    getParameterByName,
    setLinkNode,
    setTextNode
} from '../js/dom_utils.js';
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
    if(getParameterByName('family')) {
        xhttp.open("GET", getBackendUrl() + '/api/families/' + getParameterByName('family') + '/species/' + getParameterByName('species'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/species/' + getParameterByName('species'))
    }
    xhttp.send();
}

function displaySpecies(species) {
    setTextNode('name', species.name);
    setTextNode('isHallucinogenic', species.hallucinogenic);
    console.log(species)
    setTextNode('familyId', species.familyName)
    setLinkNode('edit', 'edit', '../species_edit/species_edit.html?family='
    + getParameterByName('family') + '&species=' + species.address)
}
