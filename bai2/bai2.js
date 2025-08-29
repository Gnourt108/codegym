function display(){
    let n = Number(document.getElementById("number").value);
    let arr = [];
    let i;
    let number;
    for(i = 0; i < n; i++){
        do{
            number = Number(prompt(`Nhập số nguyên thứ ${i+1}: `));
            if(arr.includes(number)){
                alert(`Số ${number} đã được nhập! Vui lòng nhập số khác`);
            }
        }while(arr.includes(number));
        arr.push(parseInt(number));
    }
    let max = arr[0];
    let index = 0;
    for(i = 1; i < arr.length; i++){
        if(max < arr[i]){
            max = arr[i];
            index = i;
        }
    }

    document.getElementsByClassName("result")[0].innerHTML = "Mảng vừa nhập là: "+ arr.join(", ");
    document.getElementsByClassName("count")[0].innerHTML = `Số lớn nhất là: ${max}, tại vị trí ${index + 1}`;

}