function draw1(){
    let result = document.getElementsByClassName("result1")[0];
    result.innerHTML = ``;
    let i, j, row;
    for(i=1; i <= 5; i++){
        row = "";
        for(j=1; j <= i; j++){
            row += '*'
        }
        result.innerHTML += row + "<br>";
    }
}

function draw2(){
    let result = document.getElementsByClassName("result2")[0];
    result.innerHTML = ``;
    let i, j, row;
    for(i=5; i >= 1; i--){
        row = "";
        for(j=1; j <= i; j++){
            row += '*'
        }
        result.innerHTML += row + "<br>";
    }
}

function draw3(){
    let result = document.getElementsByClassName("result3")[0];
    result.innerHTML = ``;
    let i, j, row, space;
    for(i=1; i <=5; i++){
        row = "";
        for(space = 1; space <= 5-i; space++){
            row += "&nbsp;&nbsp;"; //Khoảng trắng HTML
        }
        for(j = 1; j <= i; j++){
            row += "*";
        }
        result.innerHTML += row + "<br>";
    }
}

function draw4(){
    let result = document.getElementsByClassName("result4")[0];
    result.innerHTML = ``;
    let i, j, row, space;
    for(i=5; i >= 1; i--){
        row = "";
        for(space = 5; space >= i-5; space--){
            row += "&nbsp;&nbsp;"; //Khoảng trắng HTML
        }
        for(j = 1; j <= i; j++){
            row += "*";
        }
        result.innerHTML += row + "<br>";
    }
}