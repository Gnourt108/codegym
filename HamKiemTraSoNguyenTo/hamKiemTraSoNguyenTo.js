function isPrime(number){
    let i;
    if(number <= 1){
        return false;
    }
    if(number === 2){
        return true;
    }
    for ( i = 2; i <= Math.sqrt(number); i++) {
        if(number % i === 0){
            return false;
        }
    }
    return true;
}

function check(){
    let number = Number(document.getElementById('number').value);
    if(isNaN(number)){
        alert("Nhập một số")
        return
    }
    if(isPrime(number)){
        document.getElementsByClassName("result")[0].innerHTML = `${number} là một số nguyên tố`;
    }else{
        document.getElementsByClassName("result")[0].innerHTML = `${number} KHÔNG là một số nguyên tố`;
    }


}