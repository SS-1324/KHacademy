/*
* 백엔드를 거치지 않고 브라우저에서 직접 api를 호출
* - 장점 : 서버 구현 필요 없음
* - 단점 : api키가 요청 url에 그대로 노출됨.
* */

const NASA_API_KEY = "bQtxz0497fB6xAcmFbJUkTGKN3Cb6cZkim0eGwR0";
const NASA_APOD_URL = "https://api.nasa.gov/planetary/apod";

const searchBtn = document.querySelector("#apod-search-btn");
const dateInput = document.querySelector("#apod-date-input");
const resultEl = document.querySelector("#apod-client-reuslt");

if(searchBtn){
    searchBtn.addEventListener("click", async function (){
        const date = dateInput.value;
        const url = `${NASA_APOD_URL}?api_key=${NASA_API_KEY}&date=${date}`;

        resultEl.innerHTML = `<p class="empty-message">불러오는 중...</p>`;

        const reponse = await fetch(url);
        const result = await reponse.json();

        if(!reponse.ok){
            resultEl.innerHTML = `<p class="empty-message">불러오지 못했습니다!</p>`;
            return;
        }

        renderApod(result);
    });
}

function renderApod(apod){
    resultEl.innerHTML = `
        <img src="${apod.url}" alt="${apod.title}" class="apod-card_img">
        <h3 class="apod-card_title">${apod.title}<span class="apod-card_date">${apod.date}</span></h3>
        <p class="apod-card_desc">${apod.explanation}</p>
    `;
}