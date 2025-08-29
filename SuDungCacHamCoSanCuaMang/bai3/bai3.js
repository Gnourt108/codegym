function transfer(){
    let inputText = document.getElementById("inputText").value;
    let outputText = ""
    for (let char of inputText) {
        if(char === char.toUpperCase()) {
            outputText += char.toLowerCase();
        }else{
            outputText += char.toUpperCase();
        }
    }
    document.getElementsByClassName("result")[0].innerHTML = outputText;
}