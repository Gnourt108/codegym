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

function find(){
    let x = Number(document.getElementById('find').value);
    let position = [];
    let i;
    for(i = 0; i < arr.length; i++){
        if(arr[i] === x){
            position.push(i + 1);
        }
    }

    if(position.length > 0){
        document.getElementsByClassName("result")[0].innerHTML =
            `Tìm thấy số <b>${x}</b> ở vị trí: ${position.join(", ")}`;
    } else {
        document.getElementsByClassName("result")[0].innerHTML =
            `Không tìm thấy số <b>${x}</b> trong mảng.`;
    }
}