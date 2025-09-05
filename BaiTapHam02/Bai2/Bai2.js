function chuViHinhTron(r){
    return (2 * Math.PI * r).toFixed(2);
}

function dienTichHinhTron(r){
    return (Math.PI * r *r).toFixed(2)
}

function calculate(){
    let r = Number(document.getElementById("r").value)
    if(isNaN(r)){
        alert("Vui lòng nhập số")
        return
    }
    if(r < 0){
        alert("Bán kính phải lớn hơn 0")
        return
    }
    document.getElementById("result").innerHTML = `Chu vi hinh tròn là: ${chuViHinhTron(r)} <br> Diện tích hình tròn: ${dienTichHinhTron(r)}`
}