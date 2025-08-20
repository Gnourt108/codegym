function checkNumber(){
    let number = Number(document.getElementById("number").value);
    if(number > 0){
        alert(`${number} is greater than 0`)
    }else if(number < 0){
        alert(`${number} is less than 0`)
    }else{
        alert(`${number} equal 0`)
    }
}