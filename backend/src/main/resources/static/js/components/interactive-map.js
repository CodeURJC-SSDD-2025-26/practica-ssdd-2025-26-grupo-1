export function initInteractiveMap() {
    const linesButtons = document.querySelectorAll('[data-action="highlightLine"]');
    const lines = [...linesButtons].map(button => button.id);
    const allPaths = document.querySelectorAll(lines.map(id => String.raw`g[inkscape\:label="${id}"]`).join(', '));
    let activeLineId = null;

    function highlightLine(event) {
        const selectedLineId = event.currentTarget.id;

        if (activeLineId === selectedLineId) {
            activeLineId = null;
            allPaths.forEach(path => {
                path.style.opacity = 1;
            });
        } else {
            activeLineId = selectedLineId;
            allPaths.forEach(path => {
                path.style.opacity = 1;

                const visitedLine = path.getAttribute('inkscape:label');
                if (visitedLine !== selectedLineId) {
                    path.style.opacity = 0.2;
                }
            });
        }
    }

    linesButtons.forEach(lineButton => {
        lineButton.addEventListener('click', highlightLine);
    });
}