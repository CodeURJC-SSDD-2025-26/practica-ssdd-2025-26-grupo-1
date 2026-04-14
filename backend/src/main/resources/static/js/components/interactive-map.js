export function initInteractiveMap() {
    const linesButtons = document.querySelectorAll('[data-action="highlightLine"]');
    const lines = [...linesButtons].map(button => button.id);
    const allPaths = document.querySelectorAll(lines.map(id => String.raw`g[inkscape\:label="${id}"]`).join(', '));
    const searchParams = new URLSearchParams(window.location.search);
    const activeLineId = searchParams.get('lineName') || searchParams.get('newLine');

    function applyInitialHighlight(selectedLineId) {
        if (!selectedLineId || !lines.includes(selectedLineId)) {
            return;
        }

        allPaths.forEach(path => {
            path.style.opacity = 1;

            const visitedLine = path.getAttribute('inkscape:label');
            if (visitedLine !== selectedLineId) {
                path.style.opacity = 0.2;
            }
        });
    }

    function handleLineClick(event) {
        const selectedLineId = event.currentTarget.id;

        if (activeLineId === selectedLineId) {
            event.preventDefault();
            window.location.assign('/index');
        }
    }

    applyInitialHighlight(activeLineId);

    linesButtons.forEach(lineButton => {
        lineButton.addEventListener('click', handleLineClick);
    });
}