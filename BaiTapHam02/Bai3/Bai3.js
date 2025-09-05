function tinhGiaiThua(numb){
    let result = 1, i = 1
    while(i <= numb){
        result *= i
        i++
    }
    return result
}

function calculate(){
    let numb = Number(document.getElementById('numb').value);
    if(isNaN(numb)){
        alert("Vui lòng nhập một số")
        return
    }
    if(numb < 0){
        alert("Nhập số lớn hơn 0")
        return
    }
    document.getElementById('result').innerHTML = `Giai thừa của ${numb} = ${tinhGiaiThua(numb)}`;
}