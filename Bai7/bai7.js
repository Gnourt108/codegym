function tinhCuoc(){
    let f = Number(document.getElementById("F").value);
    let min = Number(document.getElementById("min").value);
    let sms = Number(document.getElementById("sms").value);
    let data = Number(document.getElementById("data").value);

    if(f < 0 || min < 0 || sms < 0 || data < 0){
        alert("Nhập giá trị lớn hơn 0");
    }else{

        //Thông số gói cố định
        let M = 300; //phút kèm
        let S = 100; //sms kèm
        let D = 10; //GB kèm
        let p_min = 700; //đ/phút vượt
        let p_sms = 200; //đ/SMS vượt
        let p_data = 12000; //đ/GB vượt

        //Tính vượt
        let Omin = Math.max(0, min - M);
        let Osms = Math.max(0, sms - S);
        let Odata = Math.max(0, data -D);

        //Tính tổng cước
        let total = f + Omin*p_min + Osms*p_sms + Odata*p_data;
        document.getElementsByClassName("result")[0].innerHTML =
            `Số phút vượt: ${Omin.toFixed(2)}<br>
             SMS vượt: ${Osms.toFixed(2)}<br>
             Data vượt: ${Odata.toFixed(2)}<br>
             Tổng tiền cước: ${total.toLocaleString("vi-VN")} (VNĐ)`;
    }
}