const editBtn = document.querySelectorAll(".hensyu");
const editorBlock = document.querySelectorAll(".editor");
const overlay = document.querySelector(".overlay");

for(let i = 0; i < editBtn.length; i++){
	editBtn[i].addEventListener("click",function(){
		editorBlock[i].style.display = "block";
		overlay.style.display = "block";
	})
}
const exitButton = document.querySelectorAll(".exit");
for(let i = 0; i < editBtn.length; i++){
	exitButton[i].addEventListener("click",function(){
		editorBlock[i].style.display = "none";
		 overlay.style.display = "none";
	})
}


//画像表示と画像アップロードを取得
//const placeLabel = document.querySelectorAll('#placeLabel');
const imageUpload = document.querySelectorAll('.image-upload');


/*placeLabel.forEach(function (placeLabel, index) {
    placeLabel.addEventListener('click', function () {
        imageUpload[index].click();
    });
});*/



    const imagePreview = document.querySelectorAll('.img');

    imagePreview.forEach(function (element, index) {
        element.addEventListener('click', function () {
            imageUpload[index].click(); // 触发文件上传点击事件
        });
    });

    imageUpload.forEach(function (element, index) {
        element.addEventListener('change', function (event) {
            const selectedImage = event.target.files[0];

            if (selectedImage) {
                const reader = new FileReader();
                reader.onload = function () {
                    imagePreview[index].src = reader.result;
                };
                reader.readAsDataURL(selectedImage);
            }
        });
    });

















    

