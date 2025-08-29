function display(){
    let numb = Number(document.getElementById("number").value);
    let i, so;
    let arr = [];
    let arr_reserve = [];
    for(i=0;i<numb;i++){
        so = Number(prompt(`Nhập số thứ ${i + 1}: `));
        arr.push(so);
    }

    for(i=arr.length-1;i>=0;i--){
        arr_reserve.push(arr[i]);
    }
    document.getElementsByClassName("before")[0].innerHTML += arr.join(", ");
    document.getElementsByClassName("after")[0].innerHTML += arr_reserve.join(", ");
}