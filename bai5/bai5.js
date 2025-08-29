function display(){
    let numb = Number(document.getElementById('number').value);
    let arr = [];
    let i;
    for(i = 0; i < numb; i++){
        let numb = Number(prompt(`Nhập số thứ ${i + 1}: `));
        arr.push(numb);
    }
    let count = 0;
    let soNguyenAm = []
    for(i = 0; i < arr.length; i++){
        if(arr[i] < 0){
            count++;
            soNguyenAm.push(arr[i]);
        }
    }
    document.getElementsByClassName("array")[0].innerHTML = `Mảng bạn nhập là: ${arr.join(", ")}`;
    document.getElementsByClassName('result')[0].innerHTML = `Tổng số nguyên âm trong mảng là: ${count} <br>
                                                                         Các số nguyên âm có trong mảng là: ${soNguyenAm.join(", ")} <br>`;
}