//문제 1. 1부터 100까지의 정수 중에서 "3의 배수이면서 5의 배수가 아닌 수"의 개수를 세어 출력하세요.
let count = 0;
for(let i=1; i<=100; i++){
    if(i % 3 === 0 && i % 5 !== 0){
        count++;
    }
}
console.log("1. 정답 : " + count);

//문제 2. 문자열을 받아 거꾸로 뒤집은 문자열을 반환하는 함수 
// `reverseStr(str)` 을 작성하세요. (배열의 `reverse()` 사용 금지, 반복문으로 직접 구현)
// hello 4~0
function reverseStr(str){
    let result = "";
    for(let i=str.length - 1; i>=0; i--){
        result += str[i];
    }
    return result;
}

console.log("2. 정답 : " + reverseStr("hello"));

//문제 3. 양의 정수를 받아 각 자리 숫자의 합을 반환하는 
// 함수 digitSum(n) 을 작성하세요. (반복문과 %, 나눗셈 활용)
function difitSum(n){
    let sum = 0;
    while(n > 0){
        sum += n % 10; // 1의자리수

        n = Math.floor(n / 10); //자리수 줄여주는 부분
    }

    return sum;
}

console.log("3. 정답 : " + difitSum(1234));

//문제 4. 아래 코드의 출력을 "실행하기 전에" 예측해 주석으로 적으세요.

console.log(typeof a); //undefined -> 선언전에 a변수를 사용하면 호이스팅으로 인해
var a = 5;

let x = 1;
function outer() {
  let x = 2;
  return function () { console.log(x); };
}
const fn = outer();
fn(); // 2 -> 함수는 선언위치의 x를 참조(렉시컬 스코프)
x = 100;
fn(); // 2 -> 전역x의 값과 fn출력하는 값은 무관하다. fn에서 출력하는 x는 fn선언시점에 클로저로 저장된 값이다.

//문제 5. 호출할 때마다 "user_1", "user_2", "user_3" … 처럼 1씩 증가하는 아이디를 반환하는 
// 함수 makeIdGenerator() 를 클로저로 작성하세요. (내부 카운터는 외부에서 접근 불가)

function makeIdGenerator(){
    let id = 0;
    return function(){
        id++;
        return "user_" + id;
    }
}

const nextId = makeIdGenerator();
console.log("5. 정답 : " + nextId());
console.log("5. 정답 : " + nextId());

//문제 6. 함수 fn 과 횟수 n 을 받아, fn 을 n 번 실행하는 고차함수 repeat(fn, n) 을 작성하세요. 
// fn 에는 현재 회차(0부터)가 인자로 전달되도록 하세요.

function repeat(fn, n){
    for(let i=1; i<=n; i++){
        fn(i);
    }
}

repeat((i) => console.log(i + "번째 실행"), 3);

const products = [
  { name: "노트북", price: 1500000 },
  { name: "마우스", price: 30000 },
  { name: "키보드", price: 90000 },
  { name: "모니터", price: 350000 },
  { name: "USB", price: 8000 },
];

//문제 7. 가격이 100000원 이상인 상품의 `name` 만 모아 배열로 만들어 출력하세요. 
// (`filter` + `map` 활용)
const pArr1 = products
                .filter(p => p.price >= 100000)
                .map(p => p.name);
console.log("7. 정답 : " + pArr1);

//문제 8. 전체 상품 가격의 "합계"와 "평균"을 `reduce` 를 사용해 구해 출력하세요.
const totalPrice = products.reduce((sum, p) => p.price + sum, 0);
console.log("8. 정답 : " + totalPrice);
console.log("8. 정답 : " + (totalPrice/products.length));

//문제 9. 가장 비싼 상품의 객체를 찾아 출력하세요. 
// (`Math.max` 없이 `reduce` 또는 반복문으로)
/*
let max = products[0];
for(let i=0; i<products.length; i++){
    max = max.price < products[i].price ? products[i] : max;
}
*/

let result = products.reduce((max, p) => {
    return max.price < p.price ? p : max;
}, products[0]);
console.log(result);

//문제 10. 아래 두 객체를 병합하되, 
// 같은 key는 `update` 의 값이 우선되도록 스프레드로 새 객체 `merged` 를 만드세요. 
// 그 후 구조분해로 `name` 과 `age` 만 추출해 출력하세요.


const base = { name: "최지원", age: 20, city: "안양" };
const update = { age: 21, job: "강사" };

const merged = {...base, ...update};
const {name, age} = merged;
console.log("10. 정답 : ",name,age);

//문제 11. 문자열 배열을 받아 각 요소(단어)의 등장 횟수를 
// 객체로 집계하는 함수 `countWords(arr)` 를 작성하세요.

function countWords(arr){
    const result = {};
    for(const word of arr){
        /*
        if(!result[word]){
            result[word] = 0;
        }
        */

        result[word] = (result[word] || 0) + 1;
    }

    return result;
}

console.log(countWords(["a", "b", "a", "c", "a", "b"]));
// { a: 3, b: 2, c: 1 }

//문제 12. 아래 `origin` 객체를 JSON 문자열로 변환했다가 
// 다시 객체 `copy` 로 복원하세요. 
// 그 후 `copy.skills` 에 `"React"` 를 추가했을 때, 
// 원본 `origin.skills` 가 영향을 받지 않는 것을 출력으로 확인하세요. 


const origin = { name: "최지원", skills: ["JS", "Java"] };
const copy = JSON.parse(JSON.stringify(origin));
copy.skills.push("React");
console.log(origin);
console.log(copy);

//문제 13. 나이를 받아 검사하는 함수 `checkAge(age)` 를 작성하세요.
// - `age` 가 음수면 `"나이는 음수일 수 없습니다."` 메시지로 에러를 발생(`throw`)
// - `age` 가 19 미만이면 `"미성년자입니다."` 메시지로 에러를 발생
// - 그 외에는 `"성인"` 을 반환

// 그리고 `try / catch` 로 
// `checkAge(-5)`, `checkAge(15)`, `checkAge(25)` 세 경우를 각각 호출해 결과
// (또는 에러 메시지)를 출력하세요.

function checkAge(age){
    if(age < 0) throw new Error("나이는 음수일 수 없습니다.");
    if(age < 19) throw new Error("미성년자입니다.");
    return "성인";
}

const ageArr = [-5,15,25];
for(let age of ageArr){
    try{
        console.log(checkAge(age));
    }catch(err){
        console.log(err.message);
    }
}

const stdList = [
  { name: "최지원", java: 90, db: 85, front: 95 },
  { name: "김지원", java: 80, db: 80, front: 80 },
  { name: "이지원", java: 75, db: 90, front: 88 },
  { name: "박지원", java: 100, db: 95, front: 90 },
];

//1. 각 학생 객체에 `total`(세 과목 총점) 속성을 추가한다.
for(let std of stdList){
    std.total = std.java + std.db + std.front;
}
console.log(stdList);

//2. 총점 기준 내림차순으로 정렬한다.
stdList.sort((a,b) => a.total - b.total); // 오름차순
stdList.reverse();
console.log(stdList);

//3. "1등 박지원 285점" 형식으로 등수와 함께 전원 출력한다.
stdList.forEach((std, i)=>{
    //console.log((i + 1) + "등 " + std.name, std.total + "점");
    console.log(`${i + 1}등 ${std.name} ${std.total}점`);
    //문자열을 사용할 때 ``안에서 ${변수명}으로 변수의 값을 바로 사용할 수 있음.
});


//4. 전체 평균 총점을 구하고, 평균 이상인 학생의 이름만 출력한다.
const avg = stdList.reduce((total, std) => total + std.total, 0) / stdList.length;
stdList
    .filter((std) => std.total >= avg)
    .forEach((std, i) => console.log(std.name));

//문제 15. `createGradeBook()` 함수를 작성하세요. 
//클로저를 이용해 내부에 학생 목록을 private하게 보관하고, 다음 메서드를 가진 객체를 반환합니다. 
// (내부 학생 배열은 외부에서 직접 접근할 수 없어야 합니다.)

//- `addStudent(name, scores)` : `scores` 는 `{ java, db, front }` 형태. 
// `name` 이 비어 있거나 점수가 누락되면 에러를 `throw` 하고, 정상이면 목록에 추가

//- `getRankList()` : 총점 기준 내림차순으로 정렬된 `[{ name, total }]` 배열 반환

//- `getAverage()` : 전체 학생의 평균 총점 반환 (목록이 비어 있으면 0)

//- `toJSON()` : 내부 학생 목록을 JSON 문자열로 반환

function createGradeBook(){
    const stdList = [];
    function getTotal(s){
        return s.java + s.db + s.front;
    }

    function addStudent(name, scores){
        if(!name) throw new Error("이름이 없음.");
        if(!scores || scores.java === null || scores.db === null || scores.front === null ) 
            throw new Error("점수가 없음");

        stdList.push({name, ...scores});
    }

    function getRankList(){
        const sortList = stdList
                            .map(s => {
                                const std = {name : s.name};
                                std.total = getTotal(s);
                                return std;
                            }) // {name, total}형태의 새로운 배열 생성
                            .sort((a, b) => b.total - a.total); // 총점 내림차순
        return sortList;
    }

    function getAverage(){
        const sum = stdList.reduce((total, std) => total + getTotal(std), 0);
        return sum / stdList.length;
    }

    function toJSON(){
        return JSON.stringify(stdList);
    }

    return {addStudent, getRankList, getAverage, toJSON};
}

console.log("===================================================")
const book = createGradeBook();
book.addStudent("최지원", { java: 90, db: 85, front: 95 });
book.addStudent("김지원", { java: 80, db: 80, front: 80 });
book.addStudent("박지원", { java: 100, db: 95, front: 90 });

console.log(book.getRankList()); // [{ name:"박지원", total:285 }, ...] 내림차순
console.log(book.getAverage());  // 265
console.log(book.toJSON());      // 학생 목록 JSON 문자열
book.addStudent("");  // -> 에러 발생 (이름 누락)