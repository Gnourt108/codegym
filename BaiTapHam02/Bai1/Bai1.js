function square(num){
    return num * num;
}

function checkNumber(number){
    if(isNaN(num)){
        alert("Vui lòng nhập số");
        return null;
    }
    if(document.getElementById("number").value === ""){
        alert("Bạn chưa nhập số");
        return null;
    }
    return number;
}

function calculate(){
    let num = Number(document.getElementById("number").value);
    checkNumber(num);
    document.getElementsByClassName("result")[0].innerHTML = `Bình phương của ${num} = ${square(num)}`
}