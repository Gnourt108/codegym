function transfer() {
    let vietnam = document.getElementById("VietNam");
    let english = document.getElementById("English");
    let vietArray = ["quả táo", "màu đen", "tai nghe"];
    let englishArray = ["apple", "black", "headphone"];
    let resultViet = "";
    let resultEnglish = "";

    if(vietnam.value !== ""){
        for(let i = 0; i < vietArray.length; i++){
            if(vietnam.value.trim().toLowerCase() === vietArray[i]){
                resultEnglish = englishArray[i];
                break;
            }
        }
        english.value = resultEnglish || "Không tìm thấy";
    }
    else if(english.value !== ""){
        for(let i = 0; i < englishArray.length; i++){
            if(english.value.trim().toLowerCase() === englishArray[i]){
                resultViet = vietArray[i];
                break;
            }
        }
        vietnam.value = resultViet || "Không tìm thấy";
    }
    else{
        alert("Vui lòng nhập một từ để dịch")
    }
}

function reset(){
    document.getElementById("VietNam").value = "";
    document.getElementById("English").value = "";
}