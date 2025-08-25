function play(){
    let min = Number(prompt("Bạn muốn đoán từ số nào? (Nhập số nhỏ nhất trong dãy số):"));
    let max = Number(prompt("Bạn muốn số lớn nhất trong dãy là số nào?"));

    if(isNaN(min) || isNaN(max) || min >= max) {
        alert("Khoảng nhập không hợp lệ!");
        return;
    }

    let secret = Math.floor(Math.random() * (max - min + 1)) + min;

    let attemps = 3;
    let i =1;
    let guess;
    for(i = 1; i<=attemps; i ++){
        guess = parseInt(prompt(`Lần ${i}/3 - Mời bạn nhập số: `));

        if(isNaN(guess)){
            alert("Bạn nhập không phải là số!");
            i--; //không tính lượt này
            continue;
        }

        if(guess === secret){
            alert(`Chúc mừng! Bạn đã đoán trúng số bí mật là:${secret}`);
            return;
        }else if(guess > secret){
            alert("Số bạn đoán lớn hơn số bí mật!");
        }else{
            alert("Số bạn đoán nhỏ hơn số bí mật!");
        }
    }
    document.getElementsByClassName("result")[0].innerHTML = `Đã hết lượt đoán! Đáp án đúng là: ${secret}`;
}