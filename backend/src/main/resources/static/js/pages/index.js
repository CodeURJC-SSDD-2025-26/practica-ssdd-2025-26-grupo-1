import {genericPartialLoader} from "../utils/genericPartialLoader.js";
import {initInteractiveMap} from "../components/interactive-map.js";

await genericPartialLoader('../../resources/templates/partials/_interactive_map.html','_interactive_map');
initInteractiveMap();