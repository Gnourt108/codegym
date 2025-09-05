function findMax(a, b, c){
    let max = a
    if(b>a){
        max = b
        if(c>a){
            max = c
        }
    }
    return max
}

function find(){
    let numb1 = Number(document.getElementById('numb1').value)
    let numb2 = Number(document.getElementById('numb2').value)
    let numb3 = Number(document.getElementById('numb3').value)

    if(isNaN(numb1) || isNaN(numb2) || isNaN(numb3)){
        alert("Nhập vào một số")
        return
    }

    document.getElementById('result').innerHTML = `Max = ${findMax(numb1, numb2, numb3)}`;
}