import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.bird.ExcelApp;
import com.bird.dao.StudentMapper;
import com.bird.entity.Student;
import com.bird.listener.StudentListener;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/24 13:50
 * @Description
 */
@SpringBootTest(classes = ExcelApp.class)
@RunWith(SpringRunner.class)
public class ExcelTest {
    @Resource
    private StudentMapper studentMapper;

    /**
     * @Author lipu
     * @Date 2021/3/24 13:54
     * @Description 读取Excel文档
     */
    @Test
    public void test1() {
        //获取Excel工作簿
        /**
         * 参数一:读取的Excel文件路径
         * 参数二:转换的Java实体类
         * 参数三:监听器、监听Excel读取操作
         */
        ExcelReaderBuilder builder = EasyExcel.read(
                "F:\\Data\\student.xlsx",
                Student.class,
                new StudentListener()
        );
        //获取具体的工作表Sheet
        ExcelReaderSheetBuilder sheet = builder.sheet();
        //读取工作表内容
        sheet.doRead();
    }

    /**
     * @Author lipu
     * @Date 2021/3/24 14:34
     * @Description 数据库读取数据写入到Excel里
     */
    @Test
    public void test2() {
        List<Student> studentList = studentMapper.selectList(null);
        /**
         * 参数一:写入的Excel文件路径
         * 参数二:实体对象
         */
        ExcelWriterBuilder builder =
                EasyExcel.write("F:\\Data\\write.xlsx", Student.class);
        ExcelWriterSheetBuilder sheet = builder.sheet();
        sheet.doWrite(studentList);
    }

    /**
     * @Author lipu
     * @Date 2021/5/13 14:43
     * @Description POI创建EXCEL 注意高低版本
     */
    @Test
    public void test3() throws Exception {
        //创建工作簿 HSSFWorkbook是低版本 XSSFWorkbook是高版本 高版本后缀为.xls 低版本后缀为.xlsx
        Workbook workbook=new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("sheet");
        //创建行
        Row row = sheet.createRow(0);
        //在行中创建单元格
        Cell cell = row.createCell(0);
        //在单元格中写入数据
        cell.setCellValue("张三");
        //完成创建
        workbook.write(new FileOutputStream(new File("F:\\","test.xls")));
    }

    /**
     * @Author lipu
     * @Date 2021/5/13 15:05
     * @Description POI读取EXCEL
     */
    @Test
    public void test4() throws Exception{
        File file=new File("F:\\","学生信息.xlsx");
        //创建工作簿对象 高版本
        Workbook workbook=new XSSFWorkbook(new FileInputStream(file));
        //获取工作表 获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取工作表记录数量
        int lastRowNum = sheet.getLastRowNum();
        //循环读取工作表的内容(注意有效内容的范围)
        for (int i = 1; i <= lastRowNum; i++) {
            //当前行数据
            Row row = sheet.getRow(i);
            //具体数据
            double id = row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            System.out.println("ID:"+id+"NAME:"+name);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/13 16:14
     * @Description 导出样式调研
     */
    @Test
    public void test5() throws Exception{
        //定义表头填充数据
        String[] headArray=new String[]{"编号","姓名","年龄","性别"};
        //创建工作簿 HSSFWorkbook是低版本 XSSFWorkbook是高版本 高版本后缀为.xls 低版本后缀为.xlsx
        Workbook workbook=new XSSFWorkbook();
        //设置字体样式 样式 大小 加粗/倾斜/下划线/删除线等
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 20);
        font.setBold(true);
        //设置单元格样式:边框 上下左右边框 THICK 粗边框 THIN细边框
        CellStyle baseStyle = workbook.createCellStyle();
        baseStyle.setBorderTop(BorderStyle.THICK);
        baseStyle.setBorderBottom(BorderStyle.THICK);
        baseStyle.setBorderLeft(BorderStyle.THICK);
        baseStyle.setBorderRight(BorderStyle.THICK);
        //设置单元格样式:对齐方式 水平对齐方式 垂直对齐方式 居中
        baseStyle.setAlignment(HorizontalAlignment.CENTER);
        baseStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //多样式设置 拷贝样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.cloneStyleFrom(baseStyle);
        titleStyle.setFont(font);
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.cloneStyleFrom(baseStyle);


        //创建工作表
        Sheet sheet = workbook.createSheet("sheet");
        //创建表标题
        Row titleRow = sheet.createRow(0);
        //表标题数据填充 因为要合并单元格,所以要创建多个单元格,在第一个单元格设置内容就可以。
        for (int i = 0; i < headArray.length; i++) {
            Cell titleCell = titleRow.createCell(i);
            //第一个单元格设置内容,其他不需要设置
            if (i==0){
                titleCell.setCellValue("学生信息");
            }
            //设置行高 42
            titleRow.setHeightInPoints(42);
            titleCell.setCellStyle(titleStyle);
        }
        //单元格合并 开始行 结束行 开始列 结束列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //创建表头
        Row headRow = sheet.createRow(1);
        //设置列宽 列宽参考值 一个标准英文字母宽度为256 2个汉字大约在5个256单位
        sheet.setColumnWidth(0,10*256);
        sheet.setColumnWidth(1,10*256);
        sheet.setColumnWidth(2,10*256);
        sheet.setColumnWidth(3,10*256);
        //表头数据填充
        for (int i = 0; i < headArray.length; i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(headArray[i]);
            cell.setCellStyle(headStyle);
        }
        //完成创建
        workbook.write(new FileOutputStream(new File("F:\\","test.xlsx")));
    }

    /**
     * @Author lipu
     * @Date 2021/5/14 11:35
     * @Description 使用模板创建EXCEL文档
     */
    @Test
    public void test6(){
        //读取模板
        //获取数据
        //数据绑定
        //输出文件
    }

}
