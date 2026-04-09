document.addEventListener("DOMContentLoaded", function () {
     function update_min_input() { //It makes input-min match the time set in slider-min.

        h_min = Math.floor(slider_min.value / 60);
        h_min = h_min.toString();
        if (h_min < 10) {
            h_min = "0" + h_min;
        }
        m_min = slider_min.value % 60;
        m_min = m_min.toString();
        if (m_min < 10) {
            m_min = "0" + m_min;
        }
        string_time_min = h_min + ":" + m_min;
        input_min.value = string_time_min;

        if(input_min.value > input_max.value) { //The value set in slider-min is greater than the value in slider-max, so we correct the value in input-min and then correct the value in slider-min.
            input_min.value = input_max.value;
            update_min_slider();
        }
    }

    function update_min_slider() { //It makes the value in slider-min match the value set in input-min.
        if (input_min.value > input_max.value) { //The value in input-min is greater than input-max, so we correct it before modifying the slider.
            input_min.value = input_max.value;
        }
        string_time_min = input_min.value.split(":");
        m_min = string_time_min[1];
        h_min = string_time_min[0];
        slider_min.value = parseInt(m_min) + parseInt(h_min * 60);
    }

    function update_max_input() { //It makes input-max match the time set in slider-max.

        h_max = Math.floor(slider_max.value / 60);
        h_max = h_max.toString();
        if (h_max < 10) {
            h_max = "0" + h_max;
        }
        m_max = slider_max.value % 60;
        m_max = m_max.toString();
        if (m_max < 10) {
            m_max = "0" + m_max;
        }
        string_time_max = h_max + ":" + m_max;
        input_max.value = string_time_max;

        if(input_max.value < input_min.value) {//The value set in slider-max is lower than the value in slider-min, so we correct the value in input-max and then reflect it in slider-max.
            input_max.value = input_min.value;
            update_max_slider();
        }
    }

    function update_max_slider() { //It makes the value in slider-max match the value set in input-max.
        if (input_max.value < input_min.value) { //The value in input-max is lower than input-min, so we correct it before modifying the slider.
            input_max.value = input_min.value;
        }
        string_time_max = input_max.value.split(":");
        m_max = string_time_max[1];
        h_max = string_time_max[0];
        slider_max.value = parseInt(m_max) + parseInt(h_max * 60);
    }

    let h_min = 0; //String with the hour of the minimum time.
    let m_min = 0; //String with the minute of the minimum time.
    let string_time_min = "08:00";

    let h_max = 0; //String with the hour of the maximum time.
    let m_max = 0; //String with the minute of the maximum time.
    let string_time_max = "09:00";

    let slider_min = document.getElementById("slider-min");
    let input_min = document.getElementById("input-min");

    let slider_max = document.getElementById("slider-max");
    let input_max = document.getElementById("input-max");


    slider_min.addEventListener("input", update_min_input);
    slider_min.addEventListener("dragend", update_min_input);
    input_min.addEventListener("input", update_min_slider);

    slider_max.addEventListener("input", update_max_input);
    slider_max.addEventListener("dragend", update_max_input);
    input_max.addEventListener("input", update_max_slider);

})
