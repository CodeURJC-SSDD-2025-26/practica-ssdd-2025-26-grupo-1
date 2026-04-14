/*
export async function genericPartialLoader(fileRoute, elementID){

    try{
        const route = await fetch(fileRoute);
        document.getElementById(elementID).innerHTML = await route.text();
    }
    catch (error){
        console.error("Error cargando el elemento: "+elementID+" de la ruta: "+ fileRoute,error);
    }

}
*/