function getHue(value, min, max) {
    const ratio = value / max;
    const r = Math.round(255 * ratio);
    const g = Math.round(255 * (1 - ratio));
    const b = 50;
    return `rgb(${r},${g},${b})`;
}

let heatmap = document.getElementById("_heatmap_graph");

const container = document.createElement('div');
container.className = 'card shoadow-media';
heatmap.appendChild(container);

heatmap = container;

cardHeader = document.createElement('div');
cardHeader.className = 'card-header';
heatmap.appendChild(cardHeader);

const title = document.createElement('h6');
title.className = 'text-center fw-bold';
title.textContent = heatmap_info.title;
cardHeader.appendChild(title);

const cardBody = document.createElement('div');
cardBody.className = 'card-body';
heatmap.appendChild(cardBody);

let max = 0;
let min = 0;
for (let i = 0; i < heatmap_info.rows * heatmap_info.columns; i++) {
    if (max < heatmap_info.cells[i].value) {
    max = heatmap_info.cells[i].value;
    }
}

const columnLabels = document.createElement('div');
columnLabels.className = 'd-flex flex-row justify-content-around align-items-center text-black';
for (let i = 0; i < heatmap_info.columns + 1; i++) {
    const label = document.createElement('div');
    label.className = 'd-flex justify-content-center align-items-center border col rounded-top';
    if (i != 0) {
        label.textContent = heatmap_info.columnLabels[i-1];
    } else {
        label.textContent = 'LINEAS/DIAS';
    }
    columnLabels.appendChild(label)
}
cardBody.appendChild(columnLabels);

for (let i = 0; i < heatmap_info.rows; i++) {
    const row = document.createElement('div');
    row.className = 'd-flex flex-row justify-content-between align-items-center text-black';
    
    for (let j = 0; j < heatmap_info.columns + 1; j++) {
    const column = document.createElement('div');
    if (j == 0) {
        column.textContent = heatmap_info.rowLabels[i];
        column.className = 'd-flex justify-content-center align-items-center col text-white fw-bold rounded-start';
        column.style.backgroundColor = heatmap_info.rowColors[i];
    } else {
        value = heatmap_info.cells[i*heatmap_info.columns + j - 1].value;
        column.textContent = value;
        column.className = 'd-flex justify-content-center align-items-center col';
        column.style.backgroundColor = getHue(value, min, max);
        column.title = `Estación: ${heatmap_info.rowLabels[i]}, Día: ${heatmap_info.columnLabels[j-1]}, Incidencias: ${value}`;
    }
        column.style.aspectRatio = '2/1';
        row.appendChild(column);
    }
    cardBody.appendChild(row);
}