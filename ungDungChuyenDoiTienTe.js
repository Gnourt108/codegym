document.getElementById("trans").onclick = function () {
    let from = parseInt(document.getElementById("from").value);
    let to = parseInt(document.getElementById("to").value);
    let money = parseFloat(document.getElementById("moneyBefore").value);
    let moneyBefore;
    if(money < 0){
        alert("Số tiền không được âm")
    }else{
        if(from === 0 && to === 1){
            moneyBefore = money / 26.000;
        }
        else if(from === 1 && to === 0){
            moneyBefore = money * 26.000;
        }
        else if(money < 0){
            alert("Số tiền không được âm");
        }
        else{
            moneyBefore = money;
        }
        document.getElementById("moneyAfter").value = moneyBefore.toFixed(2);
    }

}
