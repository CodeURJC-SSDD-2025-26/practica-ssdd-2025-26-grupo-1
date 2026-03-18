import {genericPartialLoader} from "../utils/genericPartialLoader.js";

genericPartialLoader('../../resources/templates/partials/_header.html', '_header');
genericPartialLoader('../../resources/templates/partials/_subheader.html', '_subheader');
genericPartialLoader('../../resources/templates/partials/_admin_table.html', '_admin_table');
genericPartialLoader('../../resources/templates/partials/_footer.html', '_footer');

/*//We get the buttons to change tables.
    let lines = document.getElementById("lines");
    let incidences = document.getElementById("incidences");
    let users = document.getElementById("users");

document.addEventListener("DOMContentLoaded", () => {

   /* //Now we give them the functionality.
    lines.addEventListener("click", () => {
        genericPartialLoader('../../resources/templates/partials/_line_table.html', '_admin_table');
        console.log("Hola");
    });

    users.addEventListener("click", () => {
        genericPartialLoader('../../resources/templates/partials/_admin_table.html', '_admin_table');
        console.log("Hola");
    });

    incidences.addEventListener("click", () => {
        genericPartialLoader('../../resources/templates/partials/_admin_table.html', '_admin_table');
        console.log("Hola");
    });

});*/