let arr = [];

function display(){
    let numb = Number(document.getElementById('number').value);
    let i;
    for(i = 0; i < numb; i++){
        let numb = Number(prompt(`Nhập số thứ ${i + 1}: `));
        arr.push(numb);
    }
    document.getElementsByClassName("array")[0].innerHTML = `Mảng bạn nhập là: ${arr.join(", ")}`;
}

function sortDown(){
    arr.sort((a, b) => b - a);
    document.getElementsByClassName("result")[0].innerHTML = `Mảng giảm dần: ${arr.join(", ")}`;
}

function sortUp(){
    arr.sort((a, b) => a - b);
    document.getElementsByClassName("result")[1].innerHTML = `Mảng tăng dần: ${arr.join(", ")}`;
}