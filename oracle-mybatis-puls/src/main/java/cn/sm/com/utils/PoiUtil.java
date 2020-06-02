package cn.sm.com.utils;
import cn.sm.com.domain.BmPeriod;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.portable.ApplicationException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiUtil {
    private PoiUtil() {
    }

    /**
     * Excel2003和Excel2007+创建方式不同
     * Excel2003使用HSSFWorkbook 后缀xls
     * Excel2007+使用XSSFWorkbook 后缀xlsx
     * 此方法可保证动态创建Workbook
     *
     * @param is
     * @return
     */
    public static Workbook createWorkbook(InputStream is) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(is);
    }

    /**
     * 导入数据获取数据列表
     *
     * @param wb
     * @return
     */
    public static List<List<Object>> getDataList(Workbook wb) {
        List<List<Object>> rowList = new ArrayList<>();
        for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = wb.getSheetAt(sheetNum);
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (null == row)
                    continue;
                List<Object> cellList = new ArrayList<>();
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    cellList.add(getCellValue(cell));
                }
                rowList.add(cellList);
            }
        }
        return rowList;
    }

    private static String getCellValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    short format = cell.getCellStyle().getDataFormat();
                    if (format == 14 || format == 31 || format == 57 || format == 58) {   //excel中的时间格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellvalue = sdf.format(date);
                    }
                    // 判断当前的cell是否为Date
                    else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
                        // 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式
                        Date date = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = formater.format(date);
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());

                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getStringCellValue().replaceAll("'", "''");
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    cellvalue = null;
                    break;
                // 默认的Cell值
                default: {
                    cellvalue = " ";
                }
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 此方法生成表头并写入表头名称
     *
     * @param nodes 节点
     * @param sheet 工作簿
     * @param style 单元格样式
     * @return 数据加载开始行
     */
    public static int generateHeader(List<HeaderNode> nodes, Sheet sheet, CellStyle style) {
        Map<RowKey, Row> hssfRowMap = new HashMap<>();
        int dataStartRow = 0;
        for (HeaderNode node : nodes) {
            if (!(node.firstRow == node.getLastCol() || node.getFirstCol() == node.getLastCol())) {
                CellRangeAddress cra = new CellRangeAddress(node.getFirstRow(), node.getLastRow(),
                        node.getFirstCol(), node.getLastCol());
                sheet.addMergedRegion(cra);
            }
            dataStartRow = dataStartRow >= node.getLastRow() ? dataStartRow : node.getLastRow();
            RowKey key = new RowKey();
            key.setFirstRow(node.getFirstRow());
            key.setLastRow(node.getLastRow());
            Row row = hssfRowMap.get(key);
            if (null == row) {
                row = sheet.createRow(node.getFirstRow());
                hssfRowMap.put(key, row);
            }
            Cell cell = row.createCell(node.getFirstCol());
            cell.setCellValue(node.getName());
            cell.setCellStyle(style);
        }
        return dataStartRow + 1;
    }



    public static class HeaderNode {
        private String name;
        private int firstRow;
        private int lastRow;
        private int firstCol;
        private int lastCol;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFirstRow() {
            return firstRow;
        }

        public void setFirstRow(int firstRow) {
            this.firstRow = firstRow;
        }

        public int getLastRow() {
            return lastRow;
        }

        public void setLastRow(int lastRow) {
            this.lastRow = lastRow;
        }

        public int getFirstCol() {
            return firstCol;
        }

        public void setFirstCol(int firstCol) {
            this.firstCol = firstCol;
        }

        public int getLastCol() {
            return lastCol;
        }

        public void setLastCol(int lastCol) {
            this.lastCol = lastCol;
        }
    }

    private static class RowKey {
        private int firstRow;
        private int lastRow;

        public int getFirstRow() {
            return firstRow;
        }

        public void setFirstRow(int firstRow) {
            this.firstRow = firstRow;
        }

        public int getLastRow() {
            return lastRow;
        }

        public void setLastRow(int lastRow) {
            this.lastRow = lastRow;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RowKey)) return false;
            RowKey key = (RowKey) o;
            return firstRow == key.firstRow &&
                    lastRow == key.lastRow;
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstRow, lastRow);
        }
    }



    /**
     * 预算查询导出科目汇总数据
     * @param list
     * @param entityName
     * @return
     */
    public static Map<String, Object> exportEntitySubjectQuery(List<LinkedHashMap<String, Object>> list, String entityName, List<BmPeriod> periodList) {
        String fileName = System.getProperty("user.dir")+"\\test.xlsx";
        short a = 2;
        short b = 1;
        Map<String, Object> rMap = new HashMap<>();
        // 第一步，创建一个webbook，对应一个Excel文件
        Workbook workbook = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = workbook.createSheet(entityName);
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setAlignment(a);
        style.setVerticalAlignment(b);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        //垂直居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置边框
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);

        //科目id
        List<HeaderNode> nodes = new ArrayList<>();
        HeaderNode headerNode00 = new HeaderNode();
        headerNode00.setName("科目id");
        headerNode00.setFirstRow(1);
        headerNode00.setLastRow(1);
        headerNode00.setFirstCol(0);
        headerNode00.setLastCol(0);
        nodes.add(headerNode00);
        //科目
        HeaderNode hasChildrenNode = new HeaderNode();
        hasChildrenNode.setName("parentId");
        hasChildrenNode.setFirstRow(1);
        hasChildrenNode.setLastRow(1);
        hasChildrenNode.setFirstCol(1);
        hasChildrenNode.setLastCol(1);
        nodes.add(hasChildrenNode);

        //层级
        HeaderNode subjectNode = new HeaderNode();
        subjectNode.setName("科目名称");
        subjectNode.setFirstRow(1);
        subjectNode.setLastRow(1);
        subjectNode.setFirstCol(2);
        subjectNode.setLastCol(2);
        nodes.add(subjectNode);
        for (int i = 1; i < periodList.size()+2; i++) {
            String name="";
            if(i==1){
                name="2020财年" ;
            }else {

                name=  String.valueOf(periodList.get(i-2).getPeriodName());
            }
            HeaderNode headerNodePeriod = new HeaderNode();
            headerNodePeriod.setName(name);//期间
            headerNodePeriod.setFirstRow(0);
            headerNodePeriod.setLastRow(0);
            headerNodePeriod.setFirstCol(i+2);
            headerNodePeriod.setLastCol(i+4);
            nodes.add(headerNodePeriod);
            //期间对应的值
            for (int j = 1; j <4; j++) {
                HeaderNode headerNode = new HeaderNode();
                if(j==1){
                    headerNode.setName("累积预算");
                }else if(j==2){
                    headerNode.setName("已发生预算");
                }else if(j==3){
                    headerNode.setName("预算余额");
                }
                headerNode.setFirstRow(1);
                headerNode.setLastRow(1);
                headerNode.setFirstCol(3*i+j);
                headerNode.setLastCol(3*i+j);
                nodes.add(headerNode);
            }
            sheet.autoSizeColumn(i);
            sheet.autoSizeColumn(i, true);
        }
       /* //期间
        for (int k = 0; k < list.size(); ++k) {
            int h = 0;
            Row row3 = sheet.createRow(k + 2);
            Set set = list.get(k).entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = String.valueOf(entry.getValue());
                //String key = String.valueOf(entry.getKey());
                Cell cell = row3.createCell(h);
                cell.setCellValue(value);
                h++;

            }
        }*/
        //**设置单元格自适应**//*
        for (int i = 0; i < nodes.size(); i++) {

            if(i==3){
                sheet.setColumnWidth(i,100 * 95);
            }else {
                sheet.setColumnWidth(i,100 * 32);}
        }

        try {
            generateHeader(nodes, sheet, style);
            FileOutputStream output = new FileOutputStream(fileName);
            workbook.write(output);
            output.flush();
            output.close();
            rMap.put("path", fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return rMap;


    }
    private static String appendBlank(String value,int hierarchy){
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < hierarchy; i++) {
            stringBuffer.append("  ");
        }
        stringBuffer.append(value.trim());
        return stringBuffer.toString();
    }
    private static boolean isLockCell(int index){
        int[] array={3,6,9,12,15,18,21,24,27,30,33,36,39};
        for (int i = 0; i < array.length; i++) {
            if(index==array[i]){
                return index==array[i];
            }
        }
        return false;
    }
}