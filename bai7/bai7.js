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

function del(){
    let x = Number(document.getElementById('find').value);
    let index = arr.indexOf(x);

    if(index === -1){
        document.getElementsByClassName('result')[0].innerHTML = `Không tìm thấy số <b>${x}</b> trong mảng.`;
        return;
    }
    for(let i = index; i < arr.length-1; i++){
        arr[i] = arr[i+1];
    }
    arr[arr.length-1] = 0;
    document.getElementsByClassName("result")[0].innerHTML =
        `Đã xóa số <b>${x}</b>. Mảng mới: ${arr.join(", ")}`;
}