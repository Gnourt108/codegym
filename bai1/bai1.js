function input(){
    let rows = Number(prompt("Nhập số lượng hàng: "));
    let cols = Number(prompt("Nhập số lượng cột: "));
    let a = []
    let i, j, n;
    for(i = 0 ; i < rows; i++) {
        a[i] = [];
        for (j = 0 ; j < cols; j++) {
            a[i][j] = Number(prompt(`Nhập phần tử [${i}][${j}]: `));
        }
    }

    //In mảng
    for(i = 0 ; i < cols; i++) {
        document.write("row "+i+"<br>");
        for (j = 0 ; j < cols; j++) {
            document.write(" "+a[i][j] + "<br>");
        }
    }
}