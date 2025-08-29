let arr1 = [];
let arr2 = [];
let arr_result = [];

function display1(){
    let numb = Number(document.getElementById('number1').value);
    let i;
    for(i = 0; i < numb; i++){
        let numb = Number(prompt(`Nhập số thứ ${i + 1}: `));
        arr1.push(numb);
    }
    document.getElementsByClassName("array")[0].innerHTML = `Mảng bạn nhập là: ${arr1.join(", ")}`;
}

function display2(){
    let numb = Number(document.getElementById('number2').value);
    let i;
    for(i = 0; i < numb; i++){
        let numb = Number(prompt(`Nhập số thứ ${i + 1}: `));
        arr2.push(numb);
    }
    document.getElementsByClassName("array")[1].innerHTML = `Mảng bạn nhập là: ${arr2.join(", ")}`;
}

function combineArray(){
    arr_result = arr1.concat(arr2);
    document.getElementsByClassName("result")[0].innerHTML = `Mảng sau khi gộp là: ${arr_result.join(", ")}`;
}