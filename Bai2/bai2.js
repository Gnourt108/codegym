function checkGrade(){
    let age = Number(document.getElementById("age").value);
    if(age >= 18){
        alert("Qualified for grade 10th");
    }else if(age < 18 && age > 0){
        alert("Not qualified for grade 10th");
    }else{
        alert("Please enter age must be greater than 0");
    }
}