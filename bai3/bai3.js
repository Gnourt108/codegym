function display(){
    let num = Number(document.getElementById('number').value);
    let i ;
    let i1 = 0, i2 = 1, next;
    let result = document.getElementsByClassName("result")[0];
    result.innerHTML = "";
    for(i=1;i<=num;i++){
        result.innerHTML += i1 + '<br>';

        next = i1 + i2;
        i1 = i2;
        i2 = next;
    }
}