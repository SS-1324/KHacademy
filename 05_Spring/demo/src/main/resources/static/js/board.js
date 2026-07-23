// 이미지 미리보기(여러개)
const imagesInput = document.querySelector("#images");
const imagePreviewList = document.querySelector("#image-preview-list");

imagesInput.addEventListener("change", function (ev){
    let images = ev.target.files;
    //input.files는 배열이 아니라 FileList 객체다
    // 배열로 변환해서 반복을 사용하면 됨. Array.from(List계열객체)
    console.log(images);
    images = Array.from(images); //배열로 변경
    images.forEach(function (file, idx){
        const reader = new FileReader();
        reader.onload = function (e){
            const li = document.createElement("li");
            const img = document.createElement("img");
            img.src = e.target.result;
            img.alt = file.name;
            li.appendChild(img);
            imagePreviewList.appendChild(li);
        }

        reader.readAsDataURL(file);
    })
})
