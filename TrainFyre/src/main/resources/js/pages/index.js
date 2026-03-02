import {genericPartialLoader} from "../utils/genericPartialLoader.js";
import {initInteractiveMap} from "../components/interactive-map.js";

genericPartialLoader("../../templates/partials/_header.html", '_header');
genericPartialLoader('../../templates/partials/_footer.html', '_footer');
await genericPartialLoader('../../templates/partials/_interactive_map.html','_interactive_map');
initInteractiveMap();
