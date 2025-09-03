function compare(){
    let arr1 = document.getElementById("array1").value;
    let arr2 = document.getElementById("array2").value;
    if(arr1 === arr2){
        document.getElementsByClassName("result")[0].innerText = "Hai chuỗi giống nhau";
    } else {
        document.getElementsByClassName("result")[0].innerText = "Hai chuỗi khác nhau";
    }
}