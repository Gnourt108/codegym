class ElectricLamp {
    constructor(status) {
        this.status = status;
    }

    getStatus() {
        return this.status ? "Bóng đèn BẬT " : "Bóng đèn TẮT ";
    }
}

class SwitchButton {
    constructor(lamp, status) {
        this._lamp = lamp;
        this._status = status;
    }

    get lamp() {
        return this._lamp;
    }
    set lamp(value) {
        this._lamp = value;
    }

    get status() {
        return this._status;
    }
    set status(value) {
        this._status = value;
    }

    connectToLamp(lamp) {
        this._lamp = lamp;
    }

    switchOff() {
        if (this._lamp) {
            this._lamp.status = false;
        }
        this._status = false;
    }

    switchOn() {
        if (this._lamp) {
            this._lamp.status = true;
        }
        this._status = true;
    }
}

let lamp = new ElectricLamp(false);
let button = new SwitchButton(lamp, false);

button.connectToLamp(lamp);
let output = document.getElementById("status");

for (let i = 1; i <= 10; i++) {
    if (i % 2 !== 0) {
        button.switchOff();
    } else {
        button.switchOn();
    }
    output.innerHTML += `Lần ${i}: ${lamp.getStatus()} <br>`;
}