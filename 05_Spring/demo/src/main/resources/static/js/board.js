// 이미지 미리보기(여러개)
const imagesInput = document.querySelector("#images");
const imagePreviewList = document.querySelector("#image-preview-list");

if(imagesInput) {
    imagesInput.addEventListener("change", function (ev) {
        let images = ev.target.files;
        //input.files는 배열이 아니라 FileList 객체다
        // 배열로 변환해서 반복을 사용하면 됨. Array.from(List계열객체)
        console.log(images);
        images = Array.from(images); //배열로 변경
        images.forEach(function (file, idx) {
            const reader = new FileReader();
            reader.onload = function (e) {
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
}

//  댓글 등록/삭제
const commentForm = document.querySelector("#comment-form");
const boardIdInput = document.querySelector("#board-key");
const commentList = document.querySelector("#comment-list");

if(commentForm) {
    commentForm.addEventListener("submit", async function (ev) {
        ev.preventDefault(); //기본 이벤트 막고 직접 처리하겠다.

        const contentInput = commentForm.querySelector('textarea');
        const content = contentInput.value.trim();

        if(!content){
            alert("댓글 내용을 입력하세요.");
            return;
        }

        const boardId = boardIdInput.value;

        try {
            const response = await fetch(`/api/board/${boardId}/comment`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "X-Requested-With": "XMLHttpRequest"
                },
                // 자바스크립트의 객체를 JSON 문자열로 직렬화.
                // @RequestBody 가 이 문자열을 다시 객체로 역직렬화해서 사용
                body: JSON.stringify({content})
            });

            const result = await response.json();

            if (!response.ok || !result.success) {
                alert(result.message || " 댓글 등록에 실패했습니다.");
                return;
            }

            appendComment(result.data);
            contentInput.value = "";
        } catch (err){
            alert("댓글 등록 중 오류가 발생했습니다.");
        }
    })
}

// 새로 댓글이 등록되면
function appendComment(comment){
    const commentTemplate = document.querySelector("#comment-template");
    const cloneComment = commentTemplate.content.cloneNode(true);

    cloneComment.querySelector(".comment-list_writer").textContent = comment.writerNickname;
    cloneComment.querySelector(".comment-list_content").textContent = comment.content;
    cloneComment.querySelector(".comment-list_date").textContent = comment.createAtStr;

    commentList.appendChild(cloneComment);
}


