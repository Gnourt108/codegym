function count(){
    let textInput = document.getElementById('textInput').value.trim();
    if(textInput === ''){
        document.getElementsByClassName('result')[0].innerHTML = 'Chuỗi bạn nhập không có số từ nào!';
        return;
    }

    let words = textInput.split(/\s+/);
// \s = một khoảng trắng (space, tab, xuống dòng).
//  \s+ = một hoặc nhiều khoảng trắng liên tiếp.
//  split(/\s+/) = tách chuỗi thành các từ, bỏ qua khoảng trắng thừa.

    document.getElementsByClassName("result")[0].innerText = "Số từ trong chuỗi là: " + words.length;

}