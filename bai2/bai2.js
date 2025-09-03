function transfer(){
    let number = Number(document.getElementById("number").value);
    let arr = [];
    let i = 0, text;

    while(i < number){
        text = prompt(`Nhập kí tự thứ ${i+1}: `);
        if(text.length === 1){
            arr.push(text);
            i++;
        }else{
            alert("Chỉ nhập kí tự!");
        }

    }
    document.getElementsByClassName("array")[0].innerHTML = `Mảng bạn vừa nhập là: ${arr.join(" ")}`;
    arr.reverse();
    document.getElementsByClassName("result")[0].innerHTML = `Mảng sau khi chuyển đổi là: ${arr.join(" ")}`;
}