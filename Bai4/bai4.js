function calculate(){
    let length = Number(document.getElementById('length').value);
    let width = Number(document.getElementById('width').value);
    let result;
    if(isNaN(length) || isNaN(width)) {
        alert("Vui lòng nhập số!")
        return;
    }
    if(length < 0 || width < 0){
        alert("Nhập cạnh có giá trị lớn hơn 0");
        reset()
    }else{
        result = length * width;
        document.getElementsByClassName("result")[0].innerHTML = `Diện tích hình chữ nhật có chiều dài bằng ${length} và chiều rộng bằng ${width} là : ${result}`;
    }
}

function reset(){
    document.getElementById('length').value = "";
    document.getElementById('width').value = "";
}