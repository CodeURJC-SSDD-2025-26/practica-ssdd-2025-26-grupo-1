export function initInteractiveMap() {
    const linesButtons = document.querySelectorAll('[data-action="highlightLine"]');
    const lines = [...linesButtons].map(button => button.id);
    const allPaths = document.querySelectorAll(lines.map(id => String.raw`g[inkscape\:label="${id}"]`).join(', '));
    let effect = null

    function highlightLine(line){
        if (effect !== highlightLine) {
            effect = highlightLine;
            allPaths.forEach(path => {
                path.style.opacity = 1;

                const visitedLine = path.getAttribute('inkscape:label');

                if(visitedLine !== line.currentTarget.id){
                    path.style.opacity = 0.2;
                }
            })
        } else {
            effect = null;
            allPaths.forEach(path => {
                path.style.opacity = 1;
            });
        }
            
        
    }

    linesButtons.forEach(lineButton => {
        lineButton.addEventListener('click', highlightLine);
    });
}