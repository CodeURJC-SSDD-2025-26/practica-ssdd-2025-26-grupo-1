let effect = null;
function highlightPath(pathName) {
    const highlightedPath = document.querySelector(`[inkscape\\:label="${pathName}"]`);
    if (effect !== highlightedPath) {
        effect = highlightedPath;
        nodos.style.opacity = 0;
        allPaths.forEach(path => {
            if (path !== highlightedPath) {
                path.style.opacity = 0.2;
            } else {
                path.style.opacity = 1;
            }
        });
    } else {
        effect = null;
        nodos.style.opacity = 1;
        allPaths.forEach(path => {
            path.style.opacity = 1;
        });
    }
}
const allPaths = document.querySelectorAll(`g[inkscape\\:label^="C-"], g[inkscape\\:label^="CIVIS"]`);
const nodos = document.querySelector(`[inkscape\\:label="Nodos"]`);
const bottons = document.querySelectorAll('#lines');
bottons.forEach(button => {
    button.addEventListener('click', () => {
        const name = button.dataset.action;
        highlightPath(name);
    });
});