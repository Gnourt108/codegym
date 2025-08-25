function check(){
    let temp;

    while(true){
        temp = Number(prompt("Nhập nhiệt độ (20~100): "));
        if(isNaN(temp)){
            alert("Vui lòng nhập một con số!");
            continue;
        }

        if(temp > 100){
            alert("Nhiệt độ quá cao! Xin vui lòng giảm nhiệt độ")
            reset();
        }
        else if(temp < 20){
            alert("Nhiệt độ quá thấp! Vui lòng tăng nhiệt độ!")
            reset();
        }
        else{
            alert("Nhiệt độ bình thường!")
            break;
        }
    }
}
function reset(){
    document.getElementById('temp').value = '';
}