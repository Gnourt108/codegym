function check(){
    let age = Number(document.getElementById("age").value);
    if(isNaN(age)){
        alert("Vui lòng nhập số!");
        return;
    }
    else{
        if(age>120 || age<0){
            alert("Đây không phải tuổi người!")
        }else{
            alert("Đây là tuổi người!")
        }
    }
}
function reset(){
    document.getElementById("age").value = "";
}