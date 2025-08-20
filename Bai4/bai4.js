function check(){
    let number1 = Number(document.getElementById("number1").value);
    let number2 = Number(document.getElementById("number2").value);
    let number3 = Number(document.getElementById("number3").value);
    let max;

    if(!Number.isInteger(number1) || !Number.isInteger(number2) || !Number.isInteger(number3)) {
        alert("You must enter integers");
        return;
    }
    max = number1;
    if(number2 > max){
        max = number2;
    }
    if(number3 > max){
        max = number3;
    }
    alert(`${max} is the greatest number`);
}