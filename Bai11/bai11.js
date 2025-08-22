function calculate() {
    let income = Number(document.getElementById("income").value);
    let result = document.getElementsByClassName("result")[0];

    if (isNaN(income) || income <= 0) {
        alert("Vui lòng nhập thu nhập hợp lệ");
        return;
    }

    let giamTru = 10000000; //Giảm trừ gia cảnh
    let thueSuat = 0.1; //10%
    let thue;

    if(income <= giamTru){
        thue = 0;
    }else {
        thue = (income - giamTru) * thueSuat;
    }

    result.innerHTML = `Thuế thu nhập cá nhân phải nộp là: ${thue.toLocaleString()}`

}