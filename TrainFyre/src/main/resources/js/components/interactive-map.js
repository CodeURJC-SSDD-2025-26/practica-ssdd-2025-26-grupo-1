export function initInteractiveMap() {
    const linesButtons = document.querySelectorAll('[data-action="highlightLine"]');
    const lines = [...linesButtons].map(button => button.id);
    const allPaths = document.querySelectorAll(lines.map(id => String.raw`g[inkscape\:label="${id}"]`).join(', '));

    let timeout;

    function highlightLine(line){

        clearTimeout(timeout);

        allPaths.forEach(path => {
            path.style.opacity = 1;

            const visitedLine = path.getAttribute('inkscape:label');

            if(visitedLine !== line.currentTarget.id){
                path.style.opacity = 0.2;
            }
        })
    }

    linesButtons.forEach(lineButton => {
        lineButton.addEv2entListener('click', highlightLine);
    });
}