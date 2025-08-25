function isPrime(number){
    for(let i= 2; i <= Math.sqrt(number); i++){
        if(number % i === 0){
            return false;
        }
    }
    return true;
}
function find(){
    let numbers = Number(document.getElementById('numbers').value);
    if(isNaN(numbers)){
        alert("Vui lòng nhập số!");
        reset();
    }
    let primes = []
    let num = 2
    while(primes.length < numbers){
        if(isPrime(num)){
            primes.push(num);
        }
        num++;
    }
    document.getElementById('result').innerText = primes.join(', ');

}
function reset(){
    document.getElementById('numbers').value = '';
    document.getElementById('result').innerText = '';
}