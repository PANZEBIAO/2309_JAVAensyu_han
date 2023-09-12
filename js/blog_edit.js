

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