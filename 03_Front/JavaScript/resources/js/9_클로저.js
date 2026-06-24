/*
    클로저
    - 함수, 그 함수가 선언된 시점의 렉시컬 환경의 조합
    즉, 내부 함수가 외부함수의 변수를 기억한다.
    외부함수가 끝나도 계속 변수를 사용할 수 있다.
*/

function getCounter(){
    let count = 0; //외부에서 직접접근 x
    
    function increase(){
        count++; 
        return count;
    }

    return increase; // 내부 함수 반환 -> 외부에서 count값에 간접접근
}

const run = getCounter();
console.log(run());
console.log(run());
console.log(run());
console.log(run());
console.log(run());
// count는 보이지않지만 increase()를 통해서만 변경이 된다. -> 캡슐화
// run이 살아있는 한 count가 메모리에 유지됨 -> 상태유지

// 호출마다 독립된 환경을 가질 수 있다.
const run1 = getCounter();
const run2 = getCounter();
console.log(run1());
console.log(run1());
console.log(run2());

function out(outValue){
    function inner(innerVlaue){
        console.log("outValue : " + outValue); // out함수 실행이 끝나도 값 유지
        console.log("innerVlaue : " + innerVlaue);
    }

    return inner;
}

const print = out('외부함수');
print('내부함수');


// 데이터 은닉
function createStore(inital = 0){
    let value = inital; // value에는 외부접근 불가
    return {
        get: () => value,
        set: (v) => {value = v;},
        increase : ()=>{value++; return value;}
    }
}

const store = createStore(10);
console.log(store.get());
store.increase();
store.set(100);
console.log(store.get());