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
                    "Content-Type": "application/json", // 서버에게 내가보내는 데이터 json이야.
                    "X-Requested-With": "XMLHttpRequest" // 이건 비동기(ajax) 요청이야라고 서버에게 전달
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

    console.log(cloneComment.querySelector("li"))
    const li = cloneComment.querySelector("li");
    li.id = `comment-${comment.commentId}`;
    cloneComment.querySelector(".comment-list_writer").textContent = comment.writerNickname;
    cloneComment.querySelector(".comment-list_content").textContent = comment.content;
    cloneComment.querySelector(".comment-list_date").textContent = comment.createAtStr;
    cloneComment.querySelector(".comment-delete-btn").dataset.commentId = comment.commentId;

    commentList.appendChild(cloneComment);
}

if(commentList){
    commentList.addEventListener("click", async function (ev){
        // if(!ev.target.classList.contains("comment-delete-btn"))
        //     return;
        // const btn = ev.target;

        //closest(선택자): 클릭한 요소로부터 부모방향으로 선택자를 찾아줌.
        const btn = ev.target.closest(".comment-delete-btn");
        if(!btn){ return;}

        if(!confirm("댓글을 삭제하시겠습니까?")){return;}

        const commentId = btn.dataset.commentId;

        try {
            const response = await fetch(`/api/comments/${commentId}`,
                {
                    method: "DELETE", // get, post, put, patch, delete
                    headers: {"X-Requested-With": "XMLHttpRequest"}
                });

            const result = await response.json();

            if (!response.ok || !result.success) {
                alert(result.message || "댓글 삭제에 실패했습니다.");
                return;
            }

            document.querySelector(`#comment-${commentId}`).remove();
        } catch (err){
            alert("댓글 삭제 중 오류가 발생했습니다.");
        }
    })
}


