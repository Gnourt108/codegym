function check(){
    let number = Number(document.getElementById("number").value);
    let result = document.getElementsByClassName("result")[0];
    result.innerHTML = "";
    if(isNaN(number)){
        alert("Vui lòng nhập một số!");
        return;
    }
    let i = 1;
    for (i = 0; i <= number; i++) {
        if(i%3 === 0 && i%5 === 0){
            result.innerHTML += `FizzBuzz <br>`
        }else if(i%5 === 0){
            result.innerHTML += `Buzz <br>`
        }else if(i%3 === 0){
            result.innerHTML += `Fizz <br>`
        }else{
            result.innerHTML += `${i} <br>`
        }
    }
}