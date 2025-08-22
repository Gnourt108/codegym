function calculate(){
    let a = Number(document.getElementById('a').value);
    let b = Number(document.getElementById('b').value);
    let result;
    if(isNaN(a) || isNaN(b)) {
        alert("Vui lòng nhập số!");
        reset()
        return;
    }
    if(a===0 && b===0){
        document.getElementsByClassName("result")[0].innerHTML = "Phương trình 0x+0=0 vô số nghiệm";
    }else if(a===0 && b!==0){
        document.getElementsByClassName("result")[0].innerHTML = `Phương trình 0x + ${b} = 0 vô nghiệm`;
    }else{
        result = b / a;
        document.getElementsByClassName("result")[0].innerHTML = `Phương trình ${a}x + ${b} = 0 có nghiệm x = ${result}`;
    }
}
function reset(){
    document.getElementById("a").value = "";
    document.getElementById("b").value = "";
}