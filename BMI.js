document.getElementById("btn").onclick = function(){
    let height = Number(document.getElementById("height").value);
    let weight = Number(document.getElementById("weight").value);
    let bmi;
    let checkHeightAndWeight = height <= 0 || weight <= 0
    if(checkHeightAndWeight){
        alert("Số cân nặng và chiều cao của bạn phải lớn hơn 0 và không được bằng 0");
    }else{
        bmi = weight / (Math.pow(2, height));
        if(bmi < 18.5){
            alert("Underweight");
        }else if(bmi < 25.0){
            alert("Normal");
        }else if(bmi < 30.0){
            alert("Overweight");
        }else{
            alert("Obese");
        }
    }
}