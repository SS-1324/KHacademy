/*
    localstorage
    - 브라우저에 key-value형태로 데이터를 저장할 수 있는 공간
    - 저장된 데이터는 브라우저의 도메인별로 저장됨.
    - 5MB까지 저장할 수 있다.
*/

// ---- 전역변수
let todos = [];

// 전역으로 사용할 요소
let todoInput; // 입력창
let todoList; // 할일목록

//dom이 다 그려지면 실행.
window.addEventListener("DOMContentLoaded", function(){
    //dom요소 set
    todoInput = document.querySelector("#todo-input");
    todoList = document.querySelector("#todo-list");

    //추가버튼을 가져온다.
    const addBtn = document.querySelector('#todo-add-btn');
    addBtn.addEventListener('click', addTodo);

    //todos목록 그리기
    render();
});

// 입력창의 할일을 가져와 todos에 추가하는 함수
function addTodo(){
    const title = todoInput.value;
    if(!title) return;

    const todo = {
        id: Date.now(),
        title, //title: title, key:value가 같을 때 축약가능
        completed: false,
        createAt: new Date().toLocaleString(),
    }

    todos.push(todo);
    todoInput.value = "";
    
    render();
}

// todos를 활용해서 todolist ui를 구성하는 함수
function render(){
    todoList.textContent = "";

    if(todos.length === 0){ //할일목록 없을 때
        const emptyEl = document.createElement('li');
        emptyEl.className = 'empty-state';
        emptyEl.textContent = "할 일이 없습니다.";
        todoList.appendChild(emptyEl);
    } else { //할일목록 있을 때
        todos.forEach(function(todo, idx){
            /*
             <li>
                <div class="normal-checkbox"></div>
                <span>js 공부하기</span>
                <button class="btn-delete">삭제</button>
            </li>
            */
            const todoEl = document.createElement('li');
            todoEl.className = todo.completed ? 'completed' : '';

            const checkBox = document.createElement('div');
            checkBox.className = 'normal-checkbox' + (todo.completed ? ' checked' : '');


            todoEl.appendChild(checkBox);
        })
    }
}