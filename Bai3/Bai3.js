function check2Num(num1, num2){
    let num = 0;
    if (num1 > num2){
        alert(`${num1} lớn hơn ${num2}`);
    }else if(num1 < num2){
        num = num1 + num2;
        document.getElementsByClassName("result")[0].innerHTML = "Kết quả: "+num;
    }else{
        alert("Hai số bằng nhau")
    }
}

function check(){
    let num1 = Number(document.getElementById("number1").value);
    let num2 = Number(document.getElementById("number2").value);
    if(isNaN(num1) || isNaN(num2) || document.getElementById("number1").value === "" || document.getElementById("number2").value === ""){
        alert("Vui lòng nhập một số");
        return;
    }
    check2Num(num1, num2);
}