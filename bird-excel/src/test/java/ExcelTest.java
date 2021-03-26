import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.bird.ExcelApp;
import com.bird.dao.StudentMapper;
import com.bird.entity.Student;
import com.bird.listener.StudentListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
}
