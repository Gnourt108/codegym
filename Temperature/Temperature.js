class Temperature {
    celsius;
    constructor(celsius) {
        this.celsius = celsius;
    }

    celsiusToKelvin(){
        return (this.celsius + 273.15);
    }

    celsiusToFahrenheit(){
        return ((this.celsius * 9/5) + 32);
    }
}

function calculate(){
    let c = Number(document.getElementById("c").value);
    if(isNaN(c) || c < -273){
        alert("Nhập lại độ C")
        return;
    }
    let temperature = new Temperature(c);


    document.getElementById("Fahrenheit").innerHTML = `Fahrenheit = ${temperature.celsiusToFahrenheit()} (độ F)`;
    document.getElementById("Kelvin").innerHTML = `Kelvin: ${temperature.celsiusToKelvin()} (độ K)`;
}