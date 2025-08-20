function check(){
    let a = Number(document.getElementById("soA").value);
    let b = Number(document.getElementById("soB").value);
    let result = a % b;
    if(a % b === 0){
        alert(`${a} chia hết cho ${b}`);
    }else if(b === 0){
        alert(`Error`);
    } else{
        alert(`${a} không chia hết cho ${b} dư ${result}`);
    }
}