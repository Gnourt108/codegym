function swapArray(arr){
    let i
    let newArr = []
    for (i = arr.length - 1; i >= 0; i--) {
        newArr.push(arr[i])
    }
    return newArr
}

function checkNumber(input){

    if(input.trim() === "" || input === null){
        return "Bạn chưa nhập số"
    }
    let n = Number(input)

    if(isNaN(n)){
        return "Vui lòng nhập số hợp lệ"
    }

    if(!Number.isInteger(n)){
        return `${n} không phải là số nguyên`
    }
    return true
}



function show(){
    let numb = Number(document.getElementById('number').value);
    let n, input, check
    let arr = []
    for(let i = 0; i < numb; i++) {
        input = prompt(`Nhập vào số thứ ${i+1}: `)
        check = checkNumber(input)
        if(check !== true){
            alert(checkNumber(input))
            i--
        }
        arr.push(Number(input))
    }
    document.getElementById('beforeArray').innerHTML = `Mảng vừa nhập: ${arr.join(" ")}`
    document.getElementById('afterArray').innerHTML = `Mảng đảo ngược: ${swapArray(arr).join(" ")}`
}