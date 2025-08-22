function calculate(){
    let kwh = Number(document.getElementById("kwh").value);
    let cost = 0;

    if(kwh < 0 || isNaN(kwh)){
        alert("Vui lòng nhập số kwwh hợp lệ!");
        return;
    }

    if(kwh <= 50){
        cost = kwh * 1806;
    }else if(kwh <= 100){
        cost = 50*1806 + (kwh-50)*1866;
    }else if(kwh <= 200){
        cost = 50*1806 + 50*1866 + (kwh-100)*2167;
    }else if(kwh <= 300){
        cost = 50*1806 + 50*1866 + 100*2167 + (kwh-200)*2729;
    }else if(kwh <= 400){
        cost = 50*1806 + 50*1866 + 100*2167 + 100*2729 + (kwh-300)*3050;
    }else{
        cost = 50*1806 + 50*1866 + 100*2167 + 100*2729 + 100*3050 + (kwh-400)*3151;
    }

    document.getElementsByClassName("result")[0].innerHTML = `Số tiền điện phải trả là: ${cost.toLocaleString()} (VNĐ)`;
}