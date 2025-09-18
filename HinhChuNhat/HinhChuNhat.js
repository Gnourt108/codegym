class HinhChuNhat{
    dai;
    rong;
    constructor(dai, rong) {
        this.dai = dai;
        this.rong = rong;
    }
    tinhChuVi(){
        return (this.dai + this.rong)*2;
    }
    tinhDienTich(){
        return (this.dai * this.rong);
    }
}

function calculate(){
    let hinhChuNhat = new HinhChuNhat();
    hinhChuNhat.rong = Number(document.getElementById("rong").value);
    hinhChuNhat.dai = Number(document.getElementById("dai").value);

    document.getElementById("chuVi").innerHTML = `Chu vi = ${hinhChuNhat.tinhChuVi()}`;
    document.getElementById("dienTich").innerHTML = `Diện tích = ${hinhChuNhat.tinhDienTich()}`;
}
