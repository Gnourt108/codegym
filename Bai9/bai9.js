function check(){
    let edge1 = Number(document.getElementById("edge1").value);
    let edge2 = Number(document.getElementById("edge2").value);
    let edge3 = Number(document.getElementById("edge3").value);

    if(isNaN(edge1) || isNaN(edge2) || isNaN(edge3)){
        alert("Vui lòng nhập số");
        return;
    }
    // Kiểm tra phải là số thực (không chấp nhận số nguyên)
    if(Number.isInteger(edge1) || Number.isInteger(edge2) || Number.isInteger(edge3)){
        alert("Ba cạnh phải là số thực (không được nhập số nguyên)");
        return;
    }

    if((edge1 < 0 || edge2 < 0 || edge3 < 0) || (edge1 + edge2 < edge3) || (edge1 + edge3 < edge2) || (edge2 + edge3 < edge1)){
        alert("Ba cạnh bạn vừa nhập không tạo thành hình tam giác");
    }else{
        alert("Ba cạnh bạn vừa nhập tạo thành hình tam giác!")
    }
}