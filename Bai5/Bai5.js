let stars = ["Polaris", "Aldebaran", "Deneb", "Vega", "Altair", "Dubhe", "Regulus"];
let constellations = ["Ursa Minor", "Taurus", "Cygnus", "Lyra", "Aquila", "Ursa Major", "Leo"];
function findStar(star){
    let i ;
    for (i = 0 ; i < stars.length ; i++) {
        if(star === stars[i]){
            return constellations[i];
        }
    }
    return null;
}

function check(){
    let textInput = document.getElementById("textInput").value.trim();
    if(textInput.value !== ""){
        let constellations = findStar(textInput);
        if(constellations){
            document.getElementsByClassName("result")[0].innerHTML = `Ngôi sao ${textInput} thuộc chòm sao ${constellations}`;
        }else{
            document.getElementsByClassName("result")[0].innerHTML = `Không tìm thấy ngôi sao ${textInput} trong danh sách`;
        }
    }else{
        alert("Bạn chưa nhập tên ngôi sao");
        return;
    }
}