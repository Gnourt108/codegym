function swap(a,b){
    let temp = a
    a = b
    b = temp

    return [a,b]
}

function transfer(){
    let numb1 = Number(document.getElementById("numb1").value)
    let numb2 = Number(document.getElementById("numb2").value)


    if (document.getElementById("numb1").value === "" || document.getElementById("numb2").value === "") {
        alert("Vui lòng nhập đủ hai số");
        return;
    }

    if(isNaN(numb1) || isNaN(numb2)){
        alert("Vui lòng nhập vào một số")
        return;
    }

    if(!Number.isInteger(numb1) || !Number.isInteger(numb2)){
        alert("Số bạn nhập vào không phải số nguyên")
        return
    }

    let [newNumb1, newNumb2] = swap(numb1, numb2)

    document.getElementById("finalNumb1").value = newNumb1;
    document.getElementById("finalNumber2").value = newNumb2;
}