document.getElementById("plus").addEventListener("click", function() {
    let soA = parseFloat(document.getElementById("so1").value);
    let soB = parseFloat(document.getElementById("so2").value);
    let ketQua = soA + soB;
    document.getElementById("ketQua").value = ketQua.toFixed(2);
})

document.getElementById("minus").addEventListener("click", function() {
    let soA = parseFloat(document.getElementById("so1").value);
    let soB = parseFloat(document.getElementById("so2").value);
    let ketQua = soA - soB;
    document.getElementById("ketQua").value = ketQua.toFixed(2);
})

document.getElementById("multi").addEventListener("click", function() {
    let soA = parseFloat(document.getElementById("so1").value);
    let soB = parseFloat(document.getElementById("so2").value);
    let ketQua = soA * soB;
    document.getElementById("ketQua").value = ketQua.toFixed(2);
})

document.getElementById("divide").addEventListener("click", function() {
    let soA = parseFloat(document.getElementById("so1").value);
    let soB = parseFloat(document.getElementById("so2").value);
    if(soB === 0){
        alert("Số bị chia phải khác 0")
    }else{
        let ketQua = soA / soB;
        document.getElementById("ketQua").value = ketQua.toFixed(2);
    }

})
