function calculate(){
    let money = Number(document.getElementById("money").value);
    let term = Number(document.getElementById("term").value);
    let result = document.getElementById("result");

    if(isNaN(money)){
        alert("Vui lòng nhập số!");
        reset()
        return;
    }
    if(money < 0){
        alert("Vui lòng nhập giá trị dương");
        reset();
        return;
    }

    //lãi suất năm theo kì hạn
    let rate = {
        1 : 0.032,
        3 : 0.045,
        6 : 0.055,
        12 : 0.062
    };

    //Lãi suất tháng
    let monthRate = rate[term] / 12;

    //Lãi kép :  A = P * (1 + r)^n
    let finalAmount = money * Math.pow((1 + monthRate), term);

    //Tiền lãi
    let interest = finalAmount - money;

    result.innerHTML = `
    Kỳ hạn: ${term}<br>
    Lãi suất: ${(rate[term] * 100).toFixed(2)}%/năm<br>
    Tiền gốc: ${money.toFixed(0).toLocaleString()} (VNĐ)<br>
    Tiền lãi: ${interest.toFixed(0).toLocaleString()} (VNĐ)<br>
    Tổng tiền nhận được: ${finalAmount.toFixed(0).toLocaleString()}(VNĐ)<br>
    `
}

function reset(){
    document.getElementById("money").value = "";
    document.getElementById("term").value = "";
}