class Apple{
    constructor(){
        this.weight = 10;
    }
    getWeight(){
        return this.weight;
    }

    isEmpty() {
        return this.weight <= 0;
    }

    decreaseWeight(){
        if(!this.isEmpty())
            this.weight = this.weight - 1;
    }

}

function thongBao(message) {
    document.getElementById("status").innerHTML += message + "<br>";
}

class Human{
    name;
    gender;
    weight;

    constructor(name, gender, weight) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
    }

    eat(apple){
        if(!apple.isEmpty()){
            apple.decreaseWeight();
            this.weight++
            thongBao(`${this.name} đã ăn một miếng táo`)
        }else{
            thongBao(`Táo đã hết`)
        }
    }

    checkApple(apple){
        if(!apple.isEmpty()){
            thongBao(`Cân nặng táo hiện tại: ${apple.getWeight()}`)
        }
    }

    showInfo(){
        return `${this.name} - ${this.gender} - ${this.weight}`;
    }
}

let apple = new Apple();
let eva = new Human("Eva","Nữ",45);
let adam = new Human("Adam","Nam",60);

function showResult(){
    document.getElementById("apple").innerHTML = `Khối lượng táo còn lại: ${apple.getWeight()}`
    document.getElementById("adam").innerHTML = `Tình trạng sức khỏe: ${adam.showInfo()}`;
    document.getElementById("eva").innerHTML = `Tình trạng sức khỏe: ${eva.showInfo()}`;
}

function adamEat(){
    adam.eat(apple);
    showResult()
}

function evaEat(){
    eva.eat(apple);
    showResult()
}

