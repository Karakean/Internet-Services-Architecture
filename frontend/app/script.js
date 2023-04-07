import {getBackendUrl} from './js/configuration.js';
window.addEventListener('load', () => {

});

const sendImageForm = document.getElementById("send-image-form");
const authorInput = document.getElementById("author");
const descriptionInput = document.getElementById("description");
const fileInput = document.getElementById('file');
sendImageForm.addEventListener("submit", e => {
    e.preventDefault();

    if (!fileInput.files[0] || !authorInput.value || !descriptionInput.value) {
        alert("File, author and description are required!");
        return;
    }

    const request = new FormData();
    request.append("picture", fileInput.files[0]);
    request.append("author", authorInput.value);
    request.append("description", descriptionInput.value);
    const xhr = new XMLHttpRequest();
    xhr.open("POST", getBackendUrl() + "/api/pictures/upload");
    xhr.send(request);

    xhr.onload = () => {
        if (xhr.status === 202) {
            const imageId = JSON.parse(xhr.responseText);
            alert("Your image ID is: " + imageId + "\nPlease save it if you'd like to download it later.");
        }
    };
});

document.addEventListener("DOMContentLoaded", function(){
    var downloadImageDiv = document.getElementById("download-image")
    var downloadImageForm = document.getElementById("download-image-form");
    downloadImageForm = document.getElementById("download-image-form");
    const idInput = document.getElementById("image-id");
    downloadImageForm.addEventListener("submit", e => {
        e.preventDefault();
        if (!idInput.value) {
            alert("ID of file is required!");
            return;
        }

        const request = new FormData();
        const xhr = new XMLHttpRequest();
        xhr.open("GET", getBackendUrl() + "/api/pictures/download/" + idInput.value);
        xhr.send(request);

        xhr.onload = () => {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                const byteArray = response.resource;
                const author = response.author;
                const description = response.description;

                const imageElement = document.createElement("img");
                imageElement.src = "data:image/png;base64,"+byteArray
                imageElement.author = author;
                imageElement.description = description;
                downloadImageDiv.appendChild(imageElement);
                var author_el = document.createElement("p");
                var description_el = document.createElement("p");
                author_el.innerHTML = "Author: " + author;
                description_el.innerHTML = "Description: " + description;
                downloadImageDiv.appendChild(author_el);
                downloadImageDiv.appendChild(description_el);
            }
        };
    });
    var galleryLink = document.getElementById("show-gallery-link")
    var galleryDiv = document.getElementById("gallery-div")
    galleryLink.addEventListener("click", e => {
        e.preventDefault();
        const xhr = new XMLHttpRequest();
        xhr.open("GET", getBackendUrl() + "/api/pictures/download");
        xhr.send();

        xhr.onload = () => {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                const pictures = response.pictures;

                for (let i = 0; i < pictures.length; i++) {
                    const byteArray = pictures[i].resource;
                    const author = pictures[i].author;
                    const description = pictures[i].description;

                    const imageElement = document.createElement("img");
                    imageElement.src = "data:image/png;base64,"+byteArray
                    imageElement.author = author;
                    imageElement.description = description;
                    galleryDiv.appendChild(imageElement);
                    var author_el = document.createElement("p");
                    var description_el = document.createElement("p");
                    author_el.innerHTML = "Author: " + author;
                    description_el.innerHTML = "Description: " + description;
                    galleryDiv.appendChild(author_el);
                    galleryDiv.appendChild(description_el);
                }
            }
        };
    });

  });
