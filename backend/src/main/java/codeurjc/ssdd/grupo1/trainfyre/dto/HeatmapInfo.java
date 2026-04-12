package codeurjc.ssdd.grupo1.trainfyre.dto;

import java.util.List;

public class HeatmapInfo {
    private String title;
    private int rows;
    private int columns;
    private List<String> rowLabels;
    private List<String> rowColors;
    private List<String> columnLabels;
    private List<Cell> cells;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getColumns() {
        return columns;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public List<String> getRowLabels() {
        return rowLabels;
    }
    public void setRowLabels(List<String> rowLabels) {
        this.rowLabels = rowLabels;
    }
    public List<String> getRowColors() {
        return rowColors;
    }
    public void setRowColors(List<String> rowColors) {
        this.rowColors = rowColors;
    }
    public List<String> getColumnLabels() {
        return columnLabels;
    }
    public void setColumnLabels(List<String> columnLabels) {
        this.columnLabels = columnLabels;
    }
    public List<Cell> getCells() {
        return cells;
    }
    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
