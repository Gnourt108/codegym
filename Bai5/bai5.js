function check(){
    let testScore = Number(document.getElementById("testScore").value);
    let midtermGrade = Number(document.getElementById("midtermGrade").value);
    let finalGrade = Number(document.getElementById("finalGrade").value);
    if(testScore < 0 || testScore > 10 || midtermGrade < 0 || midtermGrade > 10 || finalGrade < 0 || finalGrade > 10) {
        alert("Grade must be greater than 0 and less than 10");
    }else{
        let avg = (testScore + midtermGrade * 2 + finalGrade * 3)/6;
        if(avg < 3.5){
            alert(`${avg.toFixed(2)} is : Least student`);
        }else if(avg < 5){
            alert(`${avg.toFixed(2)} is : Weak student`);
        }else if(avg < 6.5){
            alert(`${avg.toFixed(2)} is : Average student`);
        }else if(avg < 8){
            alert(`${avg.toFixed(2)} is : Good student`);
        }else{
            alert(`${avg.toFixed(2)} is : Excellent student`);
        }
    }
}