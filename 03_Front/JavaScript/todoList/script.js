/*
    localstorage
    - 브라우저에 key-value형태로 데이터를 저장할 수 있는 공간
    - 저장된 데이터는 브라우저의 도메인별로 저장됨.
    - 5MB까지 저장할 수 있다.
*/

// ---- 전역변수
let todos = JSON.parse(localStorage.getItem('todos')) || [];
let filterState = 'all';

// 전역으로 사용할 요소
let todoInput; // 입력창
let todoList; // 할일목록
let todoCount; // 남은할일개수표시
let filterButtons; // 필터버튼목록 

//dom이 다 그려지면 실행.
window.addEventListener("DOMContentLoaded", function () {
    //dom요소 set
    todoInput = document.querySelector("#todo-input");
    todoList = document.querySelector("#todo-list");
    todoCount = document.querySelector('#todo-count');
    filterButtons = document.querySelectorAll('.filter-buttons button');

    //이벤트 연결
    bindEvents();

    //todos목록 그리기
    render();
});

//dom요소와 이벤트 핸들러를 연결해주는 함수
function bindEvents(){
    const addBtn = document.querySelector('#todo-add-btn');
    addBtn.addEventListener('click', addTodo);

    const clearBtn = document.querySelector('#clear-completed-btn');
    clearBtn.addEventListener('click', clearCompletedTodos);

    todoInput.addEventListener('keydown', function(e){
        if(e.key === 'Enter'){
            addTodo();
        }
    })

    filterButtons.forEach(function(btn){
        btn.addEventListener('click', function(){
            setFilter(btn.dataset.filter);
        })
    })
    /*
        dataset
        - html요소의 data-~~ 속성에 값을 저장 후 스크립트객체를 통해 가져올 수 있음.
        - html에서 data-filter='all'와 같이 작성해두면
          js에서 요소.dataset.filter로 all값을 가져올 수 있음.
    */
}

//todos -> 내가 클릭한 값빼고 제거한 목록을 표시
function setFilter(type){
    filterState = type;

    filterButtons.forEach(function(btn, idx){
        btn.className = 'btn-normal' + (btn.dataset.filter === type ? ' active' : '');
    });

    render();
}

function clearCompletedTodos() {
    //완료목록 전부 제거(todo.completed === true)
    todos = todos.filter(todo => !todo.completed);
    saveTodos();
    render();
}

// 입력창의 할일을 가져와 todos에 추가하는 함수
function addTodo() {
    const title = todoInput.value;
    if (!title) return;

    const todo = {
        id: Date.now(),
        title, //title: title, key:value가 같을 때 축약가능
        completed: false,
        createAt: new Date().toLocaleString(),
    }

    todos.push(todo);
    todoInput.value = "";

    saveTodos();
    render();
}

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}

function getFilteredTodos(){
    if(filterState === 'active') return todos.filter(todo => !todo.completed);
    else if(filterState === 'completed') return todos.filter(todo => todo.completed);
    return todos;
}
// todos를 활용해서 todolist ui를 구성하는 함수
function render() {
    todoList.textContent = "";

    const filteredTodos = getFilteredTodos();

    if (filteredTodos.length === 0) { //할일목록 없을 때
        emptyStateRender();
    } else { //할일목록 있을 때
        filteredTodos.forEach(function (todo, idx) {
            todoItemRender(todo);
        });
    }

    const count = todos.filter(todo => !todo.completed).length;
    todoCount.textContent = `${count}개 남음`;
}

function emptyStateRender() {
    const emptyEl = document.createElement('li');
    emptyEl.className = 'empty-state';
    emptyEl.textContent = "할 일이 없습니다.";
    todoList.appendChild(emptyEl);
}

function todoItemRender(todo) {
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
    checkBox.addEventListener('click', () => toggleTodoState(todo.id));

    //XSS방지 : innerHTML대신 textContent로 텍스트만 주입
    //innerHTML사용시 입력창에 <script src"...."></script>같은 입력시 실행됨.
    const contentSpan = document.createElement('span');
    contentSpan.textContent = todo.title;

    const deleteBtn = document.createElement('button');
    deleteBtn.className = 'btn-delete';
    deleteBtn.textContent = '삭제';
    deleteBtn.addEventListener('click', () => todoDelete(todo.id));

    todoEl.appendChild(checkBox);
    todoEl.appendChild(contentSpan);
    todoEl.appendChild(deleteBtn);

    todoList.appendChild(todoEl);
}

function todoDelete(id) {
    //전달받은 id와 같은 todo는 todos에서 제거
    todos = todos.filter(t => t.id !== id);
    saveTodos();
    render();
}

function toggleTodoState(id) {
    todos = todos.map((todo) => {
        if (todo.id === id) {
            todo.completed = !todo.completed;
        }
        return todo;
    })

    saveTodos();
    render();
}