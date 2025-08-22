function calculate(){
    let edge1 = Number(document.getElementById('edge1').value);
    let edge2 = Number(document.getElementById('edge2').value);
    let result;
    if(isNaN(edge1) || isNaN(edge2)){
        alert("Vui lòng nhập số!")
        return;
    }
    if(edge1 < 0 || edge2 < 0){
        alert("Nhập cạnh có giá trị lớn hơn 0");
        reset()
    }else{
        result = (edge1 * edge2)/2;
        document.getElementsByClassName("result")[0].innerHTML = `Diện tích hình tam giác vuông có cạnh thứ nhất bằng ${edge1} và cạnh thứ hai bằng ${edge2} là : ${result.toFixed(2)}`;
    }
}

function reset(){
    document.getElementById('edge1').value = "";
    document.getElementById('edge2').value = "";
}