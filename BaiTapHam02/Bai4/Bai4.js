function checkText(c){
    if(c >= "0" && c <= "9"){
        return true
    }else return false
}
function check(){
    let text = document.getElementById("inputText").value
    if(checkText(text)){
        document.getElementById("result").innerHTML = 'Ký tự số'
    }else{
        document.getElementById("result").innerHTML = 'Không phải ký tự số'
    }
}