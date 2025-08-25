function calculate(){
    let number = Number(document.getElementById('number').value);
    let display = document.getElementsByClassName('display')[0];
    display.innerHTML = "";
    if(isNaN(number)){
        alert("Vui lòng nhập số");
        return;
    }
    let i, i1 = 0, i2 = 1, next, sum = 0;
    for(i = 1; i <= number; i++){
        display.innerHTML += `${i1} <br>`

        sum += i1;
        next = i1 + i2;
        i1 = i2;
        i2 = next;

    }
    document.getElementsByClassName("result")[0].innerHTML = `Tổng của ${number} fibonacci đầu tiên là: ${sum}`;

}