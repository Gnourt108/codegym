function calculate(){
    let number = Number(document.getElementById("number").value);
    let display = document.getElementsByClassName("display")[0]
    display.innerHTML = "";
    let kq;
    if(isNaN(number)){
        alert("Vui lòng nhập một số!");
        return;
    }
    let i, sum = 0;
    for(i = 1; i <= number; i++){
        kq = i * 7
        sum += kq;
        display.innerHTML += `${kq} <br>`
    }
    document.getElementsByClassName("result")[0].innerHTML = `Tổng ${number} các số đầu tiên chia hết cho 7 là: ${sum}`

}

function reset(){
    document.getElementById("number").value = "";
    document.getElementsByClassName("display")[0].innerHTML = "";
    document.getElementsByClassName("result")[0].innerHTML = "";
}