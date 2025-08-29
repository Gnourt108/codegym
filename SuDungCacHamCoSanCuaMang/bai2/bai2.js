function display(){
    let number = Number(document.getElementById('number').value);
    let i;
    let n;
    let array = [];
    for (let i = 1; i <= number; i++) {
        n = Number(prompt(`Nhập số thứ ${i}: `));
        array.push(n);
    }
    let result = "";
    for (i = 0; i < array.length; i++) {
        result += array[i];
        if(array[i] % 2 === 0 && array[i+1] % 2 === 0){
            result += "-";
        }
    }
    document.getElementsByClassName("result")[0].innerHTML = "Kết quả "+ result;
}