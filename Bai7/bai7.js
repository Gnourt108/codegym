function calculate(){
    let a = Number(document.getElementById("a").value);
    let b = Number(document.getElementById("b").value);
    let c = Number(document.getElementById("c").value);
    let d;
    let result = document.getElementsByClassName("result")[0];

    if(isNaN(a) || isNaN(b) || isNaN(c)) {
        alert("Vui lòng nhập số");
        return;
    }
    else{
        d = Math.pow(b,2) - 4 * a * c;
        if(d<0){
            result.innerHTML = `Phương trình vô nghiệm`;
        }else if(d>0){
            let x1 = (-b + Math.sqrt(d))/(2*a);
            let x2 = (-b - Math.sqrt(d))/(2*a);
            result.innerHTML = `Phương trình có hai nghiệm x1 = ${x1.toFixed(2)} và x2 = ${x2.toFixed(2)}`;
        }else{
            let x = (-b)/(2*a);
            result.innerHTML = `Phương trình có nghiệm kép x1 = x2 = ${x.toFixed(2)}`;
        }
    }
}

function reset () {
    document.getElementById("a").value = "";
    document.getElementById("b").value = "";
    document.getElementById("c").value = "";
}