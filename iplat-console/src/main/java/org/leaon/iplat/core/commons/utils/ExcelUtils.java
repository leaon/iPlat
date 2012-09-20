package com.neusoft.lbmp.ui.uifc.action.wap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.leaon.iplat.core.commons.exception.BizException;

public class ExcelUtils {

    /**
     * 取得有多少SHEET页
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    // private static int number=0;
    public int getsheetnum(final File path) throws FileNotFoundException, IOException {
        int sheetnum = 0;
        HSSFWorkbook wb = null;
        POIFSFileSystem fs = null;
        fs = new POIFSFileSystem(new FileInputStream(path));
        wb = new HSSFWorkbook(fs);
        sheetnum = wb.getNumberOfSheets();
        // System.out.println("=======" + sheetnum);
        return sheetnum;
    }

    /**
     * 获取指定excel文件的数据，并返回一个List<MSSpace>
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public Map getExcelData(File path) throws FileNotFoundException, IOException,
            BizException {
        int sheetnum = 0;
        HSSFWorkbook wb = null;
        POIFSFileSystem fs = null;
        fs = new POIFSFileSystem(new FileInputStream(path));
        wb = new HSSFWorkbook(fs);
        sheetnum = wb.getNumberOfSheets();
        HSSFRow row;
        HSSFCell cell;
        HSSFSheet sheet;
        String val;
        Map map = new HashMap();
        for (int i = 0; i < sheetnum; i++) {
            sheet = wb.getSheetAt(i);
            int max = sheet.getLastRowNum();
            for (int y = 1; y <=max; y++) {// 头一行为标题,可以去掉不处理
                row = sheet.getRow(y);
                cell = row.getCell((short) 0);
                Wap wap = new Wap();
                val = getCellValue(cell, wb.getSheetName(i), y, 1);
                if (val == null || val.length() == 0 || validateWapUrl(val) == false ) {
                	System.out.println(wb.getSheetName(i) + "中的第" + (y+1) + "行第1列的数据为空或类型不对,IP地址类型错误！");
                    throw new BizException(wb.getSheetName(i) + "中的第" + (y+1) + "行第1列的数据为空或类型不对,IP地址类型错误！");
                } else {
                	if(map.containsKey(val))
                    {
                		System.out.println(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据已存在！");
                        throw new BizException(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据已存在！");
                    }else{
                    wap.setWapUrl(val);
                    }
                }
                cell = row.getCell((short) 1);
                val=getCellValue(cell, wb.getSheetName(i), y, 2);
                if (val == null || val.length() == 0 || validateWapName(val) == false) {
                	System.out.println(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据为空或类型不对,Wap名称过长！");
                    throw new BizException(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据为空或类型不对,Wap名称过长！");
                } else {
                    wap.setWapName(val);
                } 
                cell = row.getCell((short) 2);
                val=getCellValue(cell, wb.getSheetName(i), y, 3);
                if (val == null || val.length() == 0 || validateDescText(val) == false) {
                	System.out.println(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据为空或类型不对,Wap描述过长！");
                    throw new BizException(wb.getSheetName(i) + "中的第" + (y+1) + "行第2列的数据为空或类型不对,Wap描述过长！");
                } else {
                    wap.setDescText(val);
                } 
                map.put(wap.getWapUrl(), wap);
                
            }
        }
        return map;
    }
    //WapUrl校验
    public boolean validateWapUrl(String ip) {

		boolean wapUrlValidate = ip.matches(
				"^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
		if (wapUrlValidate ) {
			return true;
		} else {
			return false;
		}

	}
     //WapName校验
    public boolean validateWapName(String name) {
    	
		boolean wapNameValidate = (name!= null && name
				.length() < 50) ? true : false;

		if (wapNameValidate ) {
			return true;
		} else {
			return false;
		}
	}
    //WapName校验
    public boolean validateDescText(String descText) {
    	
		boolean descTextValidate = (descText!= null && descText
				.length() < 100) ? true : false;

		if (descTextValidate) {
			return true;
		} else {
			return false;
		}
	}
    private String getCellValue(HSSFCell cell, String sheetName, int row, int col)
            throws BizException {
        if (cell != null) {
            int cellType = cell.getCellType();
            switch (cellType) {
            // 单元格类型为数字
            case HSSFCell.CELL_TYPE_NUMERIC:
                // 取数字单元格的值
                double d = cell.getNumericCellValue();
                String s = String.valueOf(d);
                int pos = s.indexOf(".");
                return s.substring(0, pos).trim();
                // 单元格类型为字符串
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue() == null ? "" : cell.getStringCellValue().trim();
            default:
                throw new BizException(sheetName + "中的第" + row + "行第" + col + "列的数据类型不被支持！");
            }
        } else {
            throw new BizException(sheetName + "中的第" + row + "行第" + col + "列的无数据！");
        }
    }

    public HSSFWorkbook getWb(final File path) throws FileNotFoundException, IOException {
        POIFSFileSystem fs = null;
        HSSFWorkbook wb = null;
        fs = new POIFSFileSystem(new FileInputStream(path));
        wb = new HSSFWorkbook(fs);
        return wb;
    }

    public HSSFSheet getSheet(final File path, int sheetnum) throws FileNotFoundException,
            IOException {
        HSSFSheet sheet = null;
        HSSFWorkbook wb = this.getWb(path);
        sheet = wb.getSheetAt(sheetnum);
        return sheet;
    }
    
}
