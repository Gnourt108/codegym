function display(){
    let n = Number(document.getElementById("number").value);
    let arr = [];
    let i;
    let number;
    for(i = 0; i < n; i++){
        number = Number(prompt(`Nhập số nguyên thứ ${i+1}: `));
        number = parseInt(number);
        arr.push(number);
    }
    let count = 0;
    for(i = 0; i < arr.length; i++){
        if(arr[i] > 10){
            count++;
        }
    }

    document.getElementsByClassName("result")[0].innerHTML = "Mảng vừa nhập là: "+ arr.join(", ");
    document.getElementsByClassName("count")[0].innerHTML = "Tổng các số lớn hơn 10 là: "+count;

}