const colors = [
  "#4e73df",
  "#1cc88a",
  "#f6c23e",
  "#e74a3b",
  "#36b9cc",
  "#858796"
];

const graph = document.getElementById("graph");
const legend = document.getElementById("legend");

let accum = 0;

const parts = piechart_info.map((item, index) => {
  const init = accum;
  accum += item.percentage;

  const color = colors[index % colors.length];

  return `${color} ${init}% ${accum}%`;
});

graph.style.background = `conic-gradient(${parts.join(",")})`;

legend.innerHTML = piechart_info.map((item, index) => {
  const color = colors[index % colors.length];

  return `
    <div class="d-flex justify-content-between border-bottom py-2">
      <span><strong style="color:${color};">■</strong> ${item.line}</span>
      <span class="font-weight-bold">${item.percentage}%</span>
    </div>
  `;
}).join("");