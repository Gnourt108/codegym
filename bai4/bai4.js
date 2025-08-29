function draw(){
    let result = document.getElementsByClassName("result")[0];
    result.innerHTML = "";
    let rows = 7;
    let cols = 25;
    let row, i, j;

    for(i = 1; i <= rows; i++){
        row = "";
        for(j = 1; j <= cols; j++){
            if(i === 1 || i === rows || j === 1 || j === cols){
                row += "*";
            }
            else {
                row += "&nbsp;&nbsp;";
            }
        }
        result.innerHTML += row + "<br>" ;
    }
}