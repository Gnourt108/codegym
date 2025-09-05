function plus1(number){
    number += 1;
    return number;
}

function plus(){
    let number = Number(document.getElementById("number").value);
    if(isNaN(number)){
        alert("Vui lòng nhập một số");
        return
    }
    if (document.getElementById("number").value === "") {
        alert("Bạn chưa nhập số");
        return;
    }
    document.getElementsByClassName("result")[0].innerHTML = `Kết quả: ${plus1(number)}`;
}