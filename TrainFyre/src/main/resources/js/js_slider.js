

document.addEventListener("DOMContentLoaded", function () {
    function update_min_input() {
        /*if (slider_minimo.value > slider_maximo.value) {
            slider_minimo.value = slider_maximo.value;
        }*/
        h_min = Math.floor(slider_minimo.value / 60);
        h_min = h_min.toString();
        if (h_min < 10) {
            h_min = "0" + h_min;
        }
        m_min = slider_minimo.value % 60;
        m_min = m_min.toString();
        if (m_min < 10) {
            m_min = "0" + m_min;
        }
        string_hora_min = h_min + ":" + m_min;
        input_minimo.value = string_hora_min;

        if(input_minimo.value > input_maximo.value) {
            input_minimo.value = input_maximo.value;
            update_min_slider();
        }
    }

    function update_min_slider() {
        if (input_minimo.value > input_maximo.value) {
            input_minimo.value = input_maximo.value;
        }
        string_hora_min = input_minimo.value.split(":");
        m_min = string_hora_min[1];
        h_min = string_hora_min[0];
        slider_minimo.value = parseInt(m_min) + parseInt(h_min * 60);
    }

    function update_max_input() {
        /*if (slider_maximo.value < slider_minimo.value) {
            slider_maximo.value = slider_minimo.value;
        }*/
        h_max = Math.floor(slider_maximo.value / 60);
        h_max = h_max.toString();
        if (h_max < 10) {
            h_max = "0" + h_max;
        }
        m_max = slider_maximo.value % 60;
        m_max = m_max.toString();
        if (m_max < 10) {
            m_max = "0" + m_max;
        }
        string_hora_max = h_max + ":" + m_max;
        input_maximo.value = string_hora_max;

        if(input_maximo.value < input_minimo.value) {
            input_maximo.value = input_minimo.value;
            update_max_slider();
        }
    }

    function update_max_slider() {
        if (input_maximo.value < input_minimo.value) {
            input_maximo.value = input_minimo.value;
        }
        string_hora_max = input_maximo.value.split(":");
        m_max = string_hora_max[1];
        h_max = string_hora_max[0];
        slider_maximo.value = parseInt(m_max) + parseInt(h_max * 60);
    }

    let hora_min = 0;
    let h_min = 0;
    let m_min = 0;
    let string_hora_min = "08:00";

    let hora_max = 1439;
    let h_max = 0;
    let m_max = 0;
    let string_hora_max = "09:00";

    let slider_minimo = document.getElementById("slider-min");
    let show_minimo = document.getElementById("slider-min-show");
    let input_minimo = document.getElementById("min-input");

    let slider_maximo = document.getElementById("slider-max");
    let show_maximo = document.getElementById("slider-min-show");
    let input_maximo = document.getElementById("max-input");


    //slider_minimo.addEventListener("input", () => show_minimo.innerHTML = slider_minimo.value);
    slider_minimo.addEventListener("input", update_min_input);
    slider_minimo.addEventListener("dragend", update_min_input);
    //input_minimo.addEventListener("input", () => show_minimo.innerHTML = input_minimo.value);
    input_minimo.addEventListener("input", update_min_slider);

    slider_maximo.addEventListener("input", update_max_input);
    slider_maximo.addEventListener("dragend", update_max_input);
    input_maximo.addEventListener("input", update_max_slider);

    console.log(input_minimo.value);
})




