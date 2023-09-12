document.querySelector('.next').addEventListener('click', e => {
    if (queryObj.page < Math.ceil(totalCount / queryObj.per_page)) {
        queryObj.page++
        document.querySelector('.page-now').innerHTML = `第 ${queryObj.page} 页`
        setArtileList()
    }
})
document.querySelector('.last').addEventListener('click', e => {
    if (queryObj.page > 1) {
        queryObj.page--
        document.querySelector('.page-now').innerHTML = `第 ${queryObj.page} 页`
        setArtileList()
    }
})



const placeLabel = document.getElementById('placeLabel');
const imageUpload = document.querySelector('.image-upload');


placeLabel.addEventListener('click', () => {
    imageUpload.click();
});

imageUpload.addEventListener('change', (event) => {
    const imagePreview = document.getElementById('imagePreview');
    const selectedImage = event.target.files[0];

    if (selectedImage) {
        const reader = new FileReader();
        reader.onload = function () {
            imagePreview.src = reader.result;
            imagePreview.style.display = "block";
            placeLabel.style.display = 'none';
        };
        reader.readAsDataURL(selectedImage);
    }
});

document.querySelector('.image-preview').addEventListener('click', () => {
    imageUpload.click();
})



const createButton = document.querySelector(".hensyu");
const editor = document.querySelector(".editor");
const overlay = document.querySelector(".overlay");


createButton.addEventListener("click", function () {

    overlay.style.display = "block";
    editor.style.display = "block";
});


const exitButton = document.querySelector(".exit");


exitButton.addEventListener("click", function () {

    editor.style.display = "none";
    overlay.style.display = "none";
});

