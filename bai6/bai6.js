function check(){
    let number = +document.getElementById("number").value;
    let arr = []
    let i, n
    for (i = 0; i < number; i++) {
        n = prompt(`Nhập ký tự thứ ${i+1}: `);
        if(n.length !== 1 || !n){
            alert("Chỉ được nhập một ký tự")
            i--;
        }else{
            arr.push(n);
        }
    }
    document.getElementsByClassName("array")[0].innerHTML = `Mảng vừa nhập là: ${arr.join(" ")}`;
    for (i = 0; i < arr.length; i++){
        if(arr[i]=== "-"){
            arr[i] = "_"
        }
    }

    document.getElementsByClassName("result")[0].innerHTML = `Mảng sau khi chuyển đổi là: ${arr.join(" ")}`;
}