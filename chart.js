var chart = document.querySelector(".chart");
const canvas = document.createElement("canvas");
const ctx = canvas.getContext("2d");
canvas.width = 150;
canvas.height = 150;
chart.appendChild(canvas);
ctx.lineWidth = 16;
const R = 60;
function drawCircle(color, ratio, anticlockwise){
  ctx.strokeStyle = color;
  ctx.beginPath();
  ctx.arc(
    canvas.width / 2,
    canvas.height / 2,
    R,
    0,
    ratio * 2 * Math.PI,
    anticlockwise
  );
  ctx.stroke();
}
function updateChart(income, expense) {
  let ratio = income / (income + expense);
  drawCircle("#28B9B5", -ratio, true);
  drawCircle("#e66767", 1 - ratio, false);
}
updateChart(0, 0);