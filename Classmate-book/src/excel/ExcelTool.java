package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelTool {
	public void outExcel(List<users> ls) {
		HSSFWorkbook wb = null;
		String sheetName = "校友录";
		String []title = new String[10];
		title[0]="学号";
		title[1]="姓名";
		title[2]="家庭住址";
		title[3]="电话";
		title[4]="微信";
		title[5]="邮箱";
		title[6]="QQ";
		title[7]="个性语言";
		title[8]="职务";
		title[9]="班级";
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<ls.size();i++){
            row = sheet.createRow(i + 1);
            
            //for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                //row.createCell(j).setCellValue(values[i][j]);
            //}
            int j=0;
            row.createCell(j++).setCellValue(ls.get(i).getUsno());
            row.createCell(j++).setCellValue(ls.get(i).getUsname());
            row.createCell(j++).setCellValue(ls.get(i).getUsaddress());
            row.createCell(j++).setCellValue(ls.get(i).getUstele());
            row.createCell(j++).setCellValue(ls.get(i).getUsweixin());
            row.createCell(j++).setCellValue(ls.get(i).getUsyouxiangadd());
            row.createCell(j++).setCellValue(ls.get(i).getUsqq());
            row.createCell(j++).setCellValue(ls.get(i).getUsdiy());
            row.createCell(j++).setCellValue(ls.get(i).getUspost());
            row.createCell(j++).setCellValue(ls.get(i).getUcname());
        }
        File file=new File("校友录.xls");
        try { file.createNewFile();
            //将excel写入
            FileOutputStream stream= new FileOutputStream(file);
            wb.write(stream);
            stream.close();
        } catch (IOException e) { e.printStackTrace();}
        try {
			wb.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
        
    
	}
}
