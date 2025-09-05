function findAndCount(arr, text){
    let i, count = 0
    for (i = 0; i < arr.length; i++){
        if(arr[i] === text){
            count++
        }
    }
    if(count > 0){
        return count
    }else return -1
}
let arr = []
function input(){
    let i, n
    let number = Number(document.getElementById('number').value)
    for (i = 0; i < number; i++){
        n = prompt(`Nhập kí tự thứ ${i+1}: `)
        if(n === null || n === ""){
            alert("Bạn chưa nhập kí tự")
            i--
            continue
        }
        if(n.length !== 1){
            alert("Vui lòng nhập đúng 1 kí tự")
            i--
            continue
        }
        arr.push(n)
    }
    document.getElementById('array').innerHTML = `Mảng kí tự vừa nhập: ${arr.join(" ")}`
}

function find(){
    let text = document.getElementById('textInput').value
    let result = findAndCount(arr, text.trim())
    if(result !== -1){
        document.getElementById('result').innerHTML = `Kết quả: ${text} có trong mảng và xuất hiện ${result} lần`
    }else{
        document.getElementById('result').innerHTML = `Kết quả: ${text} không trong mảng`
    }
}