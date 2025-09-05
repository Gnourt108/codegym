function checkSoNguyenDuong(numb){
    if(isNaN(numb)){
        return "Giá trị nhập vào không phải là số"
    }
    if(!Number.isInteger(numb)){
        return "Số nhập vào không phải là số nguyên"
    }
    if(numb <= 0){
        return "Số nhập vào không phải số nguyên dương"
    }
    return true
}

function check(){
    let input = document.getElementById('numb')
    let numb = Number(input.value)
    let result = checkSoNguyenDuong(numb)
    if(result === true){
        document.getElementById("result").innerHTML = `Số ${numb} là số nguyên dương`
    }else{
        document.getElementById("result").innerHTML = `${result}`
    }
}