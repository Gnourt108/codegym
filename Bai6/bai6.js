function check(){
    let amount = Number(document.getElementById('amount').value);
    if(amount < 0){
        alert("Please enter amount is greater than 0");
    }else{
        if(amount < 5000000){
            amount = 0
        }else if(amount < 10000000){
            amount *= 0.05 // 5% hoa hồng
        }else if(amount < 20000000){
            amount *= 0.10 // 10% hoa hồng
        }else{
            amount *= 0.15 //15% hoa hồng
        }
        document.getElementsByClassName("result")[0].innerHTML = `Your commission is: ${amount.toFixed(2)}`;
    }
}