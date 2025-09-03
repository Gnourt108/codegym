function transfer(){
    let number = Number(document.getElementById("number").value);
    let arr = [];
    let i = 0, text, count = 0;

    while(i < number){
        text = prompt(`Nhập kí tự thứ ${i+1}: `);
        if(text.length === 1){
            arr.push(text);
            if(text >= '0' && text <= '9'){
                count++;
            }
            i++;
        }else{
            alert("Chỉ nhập kí tự!");
        }

    }

    for (i = 0; i < arr.length; i++){

    }

    document.getElementsByClassName("array")[0].innerHTML = `Mảng bạn vừa nhập là: ${arr.join(" ")}`;
    document.getElementsByClassName("result")[0].innerHTML = `Số kí tự số (0-9) trong mảng là: ${count}`;

}