function count(){
    let count;
    let i;
    let result = document.getElementsByClassName('result')[0];
    result.innerHTML = ""; //Xóa nội dung cũ
    for (i = 1; i <= 100; i++){
        result.innerHTML += i+'<br>';
        if(i === 99){
            alert("Đã hoàn thành!")
        }
    }
}